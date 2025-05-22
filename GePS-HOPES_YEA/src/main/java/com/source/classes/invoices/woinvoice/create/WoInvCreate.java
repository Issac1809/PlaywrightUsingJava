package com.source.classes.invoices.woinvoice.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.currencyexchangerate.ICurrencyExchangeRate;
import com.source.interfaces.invoices.woinvoices.IWoInvCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.List;

import static com.constants.invoices.woinvoice.LInvCreate.*;
import static com.constants.invoices.woinvoice.LInvCreate.ACCEPT_BUTTON;
import static com.constants.invoices.woinvoice.LInvCreate.CREATE_BUTTON;
import static com.constants.invoices.woinvoice.LInvCreate.DOCUMENT_ID;
import static com.constants.invoices.woinvoice.LInvCreate.DOM_TRIGGER_SGD_INPUT;
import static com.constants.invoices.woinvoice.LInvCreate.FOREGIN_TOTAL_GST;
import static com.constants.invoices.woinvoice.LInvCreate.INVOICE_DOCUMENT_PATH;
import static com.constants.invoices.woinvoice.LInvCreate.SGD_SUB_TOTAL_INPUT;
import static com.constants.invoices.woinvoice.LInvCreate.SGD_TOTAL_GST_INPUT;
import static com.utils.SaveToTestDataJsonUtil.saveReferenceIdFromResponse;


public class WoInvCreate implements IWoInvCreate {

    Logger logger;
    PlaywrightFactory playwrightFactory;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    ICurrencyExchangeRate iCurrencyExchangeRate;
    String appUrl;

    int EUR = 4;
    int USD = 3;
    int INR = 2;
    int CAD = 2;

    String invoiceTitle = "";

    private WoInvCreate() {
    }

//TODO Constructor
    public WoInvCreate(PlaywrightFactory playwrightFactory, ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, ICurrencyExchangeRate iCurrencyExchangeRate) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iCurrencyExchangeRate = iCurrencyExchangeRate;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(WoInvCreate.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public void create() {
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
            invoiceSelectLocator.first().click();

            Locator selectCompanyLocator = page.locator(SELECT_COMPANY_LOCATOR);
            selectCompanyLocator.click();

            String woReferenceId = jsonNode.get("workOrders").get("workOrderReferenceId").asText();
            String companyId = woReferenceId.substring(0,4);
            Locator companyLocator = page.locator(getCompanyId(companyId));
            companyLocator.click();

            Locator selectTypeLocator = page.locator(SELECT_TYPE);
            selectTypeLocator.last().click();

            Locator searchFieldLocator1 = page.locator(SEARCH_FIELD);
            searchFieldLocator1.fill("Work Order");

            Locator poSelectLocator = page.locator(WO_LOCATOR);
            poSelectLocator.first().click();

            String invoiceNumber = jsonNode.get("invoices").get("woInvoiceNumber").asText();
            int randomNumber = (int) (Math.random() * 1000);
            Locator invoiceNumberLocator = page.locator(INVOICE_NUMBER_LOCATOR);
            invoiceNumberLocator.fill(invoiceNumber + randomNumber);
            invoiceTitle= invoiceNumber + randomNumber;

            Locator invoiceDateLocator = page.getByPlaceholder(DATE_PLACE_HOLDER);
            invoiceDateLocator.last().click();

            Locator todayLocator = page.locator(TODAY);
            todayLocator.last().click();

            Locator woNumberinputLocator = page.locator(WO_NUMBER_INPUT);
            woNumberinputLocator.click();

            Locator searchFieldLocator2 = page.locator(SEARCH_FIELD);
            searchFieldLocator2.fill(woReferenceId);

            Locator selectPoLocator = page.locator(getWoReferenceId(woReferenceId));
            selectPoLocator.first().click();

            page.waitForLoadState(LoadState.NETWORKIDLE);
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);

            Locator currencyCodeLocator = page.locator(CURRENCY_CODE);
            String getCurrencyCode = currencyCodeLocator.textContent();

            Locator rate = page.locator(RATE_INPUT);
            rate.fill(jsonNode.get("invoices").get("woInvocieRate").asText());
            rate.evaluate(DOM_TRIGGER_RATE_INPUT);

            playwrightFactory.savePropertiesIntoJsonFile("configSettings", "currencyCode", getCurrencyCode);
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Create function: {}", exception.getMessage());
        }
    }

    public double gst() {
//TODO Used JavaScript to get the value of the input field => page.evaluate("document.getElementById('USDgstId').value");
        double finalGSTPercentage = 0;
        try {
            Locator gstPercentageLocator = page.locator(GST_LOCATOR);
            String gst = jsonNode.get("workOrders").get("gstPercentage").asText();
            gstPercentageLocator.fill(gst);
            gstPercentageLocator.evaluate(DOM_TRIGGER_GST_INPUT);
            String gstPercentage = gst.replaceAll("[^\\d.]", "");
            finalGSTPercentage = Double.parseDouble(gstPercentage);
        } catch (Exception exception) {
            logger.error("Exception in WO GST function: {}", exception.getMessage());
        }
        return finalGSTPercentage;
    }

    public void ifSgdEnable(double finalGSTPercentage) {
        try {
            String woReferenceId = jsonNode.get("workOrders").get("workOrderReferenceId").asText();
            String currencyCode = jsonNode.get("configSettings").get("currencyCode").asText();
            if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (woReferenceId.startsWith(COMPANY_ID_2400)
                    || woReferenceId.startsWith(COMPANY_ID_5K00) || woReferenceId.startsWith(COMPANY_ID_2U00) ||
                    woReferenceId.startsWith(COMPANY_ID_2W00))) {
//TODO Foreign Sub-Total
                Locator foreignSubTotalLocator = page.locator(FOREIGN_CURRENCY_LOCATOR);
                String foreignSubTotalValue = foreignSubTotalLocator.getAttribute("value");
                String foreignSubTotal = foreignSubTotalValue.replaceAll("[^\\d.]", "");
                double finalForeignSubTotal = Double.parseDouble(foreignSubTotal);

//TODO Input Sub-Total
                double currencyExchangeRate = iCurrencyExchangeRate.findCurrency();
                double sgdEquivalentSubTotal = finalForeignSubTotal * currencyExchangeRate;

                Locator sgdInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                sgdInputLocator.fill(String.valueOf(sgdEquivalentSubTotal));

//TODO Manually trigger the input and change events to ensure JavaScript logic executes
                Locator triggerSGDInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                triggerSGDInputLocator.evaluate(DOM_TRIGGER_SGD_INPUT);

//TODO Currency Exchange Rate
                double totalCurrencyExchangeRate = sgdEquivalentSubTotal / finalForeignSubTotal;

//TODO Currency Exchange Rate * Total GST
                Locator foreignTotalGstLocator = page.locator(FOREGIN_TOTAL_GST);
                String foreignTotalGstValue = foreignTotalGstLocator.getAttribute("value");
                String foreignTotalGst = foreignTotalGstValue.replaceAll("[^\\d.]", "");
                double finalForeignTotalGst = Double.parseDouble(foreignTotalGst);
                double inputTotalGst = totalCurrencyExchangeRate * finalForeignTotalGst;
                String sgdTotalGST = String.valueOf(inputTotalGst);

//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);

                switch (currencyCode) {
                    case ("EUR"):
//TODO Round off to 4 decimal places (adjust as needed)
                        BigDecimal EURValue = new BigDecimal(getSGDValue).setScale(EUR, RoundingMode.HALF_UP);
                        Locator eurInputLocator = page.locator(SGD_TOTAL_GST_INPUT);
                        eurInputLocator.fill(String.valueOf(EURValue));
                        break;

                    case ("USD"):
//TODO Round off to 3 decimal places (adjust as needed)
                        BigDecimal USDValue = new BigDecimal(getSGDValue).setScale(USD, RoundingMode.HALF_UP);
                        Locator usdInputLocator = page.locator(SGD_TOTAL_GST_INPUT);
                        usdInputLocator.fill(String.valueOf(USDValue));
                        break;

                    case ("INR"):
//TODO Round off to 2 decimal places (adjust as needed)
                        BigDecimal INRValue = new BigDecimal(getSGDValue).setScale(INR, RoundingMode.HALF_UP);
                        Locator inrIputLocator = page.locator(SGD_TOTAL_GST_INPUT);
                        inrIputLocator.fill(String.valueOf(INRValue));
                        break;

                    case ("CAD"):
//TODO Round off to 2 decimal places (adjust as needed)
                        BigDecimal CADValue = new BigDecimal(getSGDValue).setScale(CAD, RoundingMode.HALF_UP);
                        Locator cadIputLocator = page.locator(SGD_TOTAL_GST_INPUT);
                        cadIputLocator.fill(String.valueOf(CADValue));
                        break;

                    default:
                        System.out.println("Not a valid currency code");
                        break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice SGD Enable function: {}", exception.getMessage());
        }
    }

    public int invoiceCreate() {
        int status = 0;
        try {
            status = 0;
//TODO Invoice Document
            Locator invoiceDocumentButton = page.locator(DOCUMENT_ID);
            invoiceDocumentButton.first();
            invoiceDocumentButton.setInputFiles(Paths.get(INVOICE_DOCUMENT_PATH));

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            Response woResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/VP/Invoices/Listing") && response.status() == 200,
                    acceptLocator::click
            );

            Locator woInvoiceTite = page.locator(getTitle(invoiceTitle));
            Response invoiceResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/VP/Invoices/") && response.status() == 200,
                    woInvoiceTite::click
            );
            saveReferenceIdFromResponse(invoiceResponse, "invoices", "workOrderInvoiceReferenceId");

            status = invoiceResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Invoice Create Function: {} ", exception.getMessage());
        }
        return status;
    }
}