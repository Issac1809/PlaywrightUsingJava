package com.source.classes.invoices.poinvoice.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.currencyexchangerate.ICurrencyExchangeRate;
import com.source.interfaces.invoices.poinvoices.IInvCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.List;
import static com.constants.invoices.poinvoice.LInvCreate.*;

public class InvCreate implements IInvCreate {

    Logger logger;
    PlaywrightFactory playwrightFactory;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    ICurrencyExchangeRate iCurrencyExchangeRate;

    int EUR = 4;
    int USD = 3;
    int INR = 2;

    private InvCreate() {
    }

//TODO Constructor
    public InvCreate(PlaywrightFactory playwrightFactory, ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, ICurrencyExchangeRate iCurrencyExchangeRate) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iCurrencyExchangeRate = iCurrencyExchangeRate;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(InvCreate.class);
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

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> companyIds = page.locator(COMPANY_RESULTS_LIST).allTextContents();
            int getCompanyIdSize = companyIds.size();
            for (int i = 0; i < getCompanyIdSize; i++) {
                if (poReferenceId.startsWith(COMPANY_ID_2400)) {
                    Locator string2400Locator = page.locator(get2400Id());
                    string2400Locator.last().click();
                }
                else if (poReferenceId.startsWith(COMPANY_ID_5K00)) {
                    Locator string5K00Locator = page.locator(get5K00Id());
                    string5K00Locator.last().click();
                }
                else if (poReferenceId.startsWith(COMPANY_ID_2U00)) {
                    Locator string2U00Locator = page.locator(get2U00Id());
                    string2U00Locator.last().click();
                }
                else if (poReferenceId.startsWith(COMPANY_ID_2W00)) {
                    Locator string2W00Locator = page.locator(get2W00Id());
                    string2W00Locator.last().click();
                }
            }

            Locator selectTypeLocator = page.locator(SELECT_TYPE);
            selectTypeLocator.last().click();

            Locator searchFieldLocator1 = page.locator(SEARCH_FIELD);
            searchFieldLocator1.fill("Purchase Order");

            Locator poSelectLocator = page.locator(PO_LOCATOR);
            poSelectLocator.first().click();

            String invoiceNumber = jsonNode.get("invoices").get("poInvoiceNumber").asText();
            int invoiceNumberConversion = Integer.parseInt(invoiceNumber);
            int randomNumber = (int) (Math.random() * 1000);
            Locator invoiceNumberLocator = page.locator(INVOICE_NUMBER_LOCATOR);
            invoiceNumberLocator.fill(String.valueOf(invoiceNumberConversion + randomNumber));

            Locator invoiceDateLocator = page.getByPlaceholder(DATE_PLACE_HOLDER);
            invoiceDateLocator.last().click();

            Locator todayLocator = page.locator(TODAY);
            todayLocator.last().click();

            Locator poNumberinputLocator = page.locator(PO_NUMBER_INPUT);
            poNumberinputLocator.click();

            Locator searchFieldLocator2 = page.locator(SEARCH_FIELD);
            searchFieldLocator2.fill(poReferenceId);

            Locator selectPoLocator = page.locator(getPoReferenceId(poReferenceId));
            selectPoLocator.first().click();

            Locator currencyCodeLocator = page.locator(CURRENCY_CODE);
            String getCurrencyCode = currencyCodeLocator.textContent();

            playwrightFactory.savePropertiesIntoJsonFile("configSettings", "currencyCode", getCurrencyCode);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Create function: {}", exception.getMessage());
        }
    }

    public double gst() {
//TODO Used JavaScript to get the value of the input field => page.evaluate("document.getElementById('USDgstId').value");
        double finalGSTPercentage = 0;
        try {
            Locator gstPercentageLocator = page.locator(GST_LOCATOR);
            String getGstPercentage = String.valueOf(gstPercentageLocator.evaluate(DOM_TRIGGER));
            String gstPercentage = getGstPercentage.replaceAll("[^\\d.]", "");
            finalGSTPercentage = Double.parseDouble(gstPercentage);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice GST function: {}", exception.getMessage());
        }
        return finalGSTPercentage;
    }

    public void ifSgdEnable(double finalGSTPercentage) {
        try {
            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            String currencyCode = jsonNode.get("configSettings").get("currencyCode").asText();
            if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (poReferenceId.startsWith(COMPANY_ID_2400) || poReferenceId.startsWith(COMPANY_ID_5K00) || poReferenceId.startsWith(COMPANY_ID_2U00) || poReferenceId.startsWith(COMPANY_ID_2W00))) {

//TODO Foreign Sub-Total
            Locator foreignSubTotalLocator = page.locator(GST_LOCATOR);
            String foreignSubTotal = foreignSubTotalLocator.getAttribute("value");
            String getForeignSubTotal = foreignSubTotal.replaceAll("[^\\d.]", "");
            double finalForeignSubTotal = Double.parseDouble(getForeignSubTotal);

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
            Locator foreignTotalGst = page.locator(GST_LOCATOR);
            String getForeignTotalGst = foreignTotalGst.getAttribute("value");

            String convertedForeignTotalGst = getForeignTotalGst.replaceAll("[^\\d.]", "");
            double finalForeignTotalGst = Double.parseDouble(convertedForeignTotalGst);
            double inputTotalGst = totalCurrencyExchangeRate * finalForeignTotalGst;
            String sgdTotalGST = String.valueOf(inputTotalGst);
//TODO Keep only digits and the decimal point
            String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
            double getSGDValue = Double.parseDouble(replaceSGDTotalGST);

            switch(currencyCode){
                case ("EUR") :
//TODO Round off to 4 decimal places (adjust as needed)
                    BigDecimal EURValue = new BigDecimal(getSGDValue).setScale(EUR, RoundingMode.HALF_UP);
                    Locator eurInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                    eurInputLocator.fill(String.valueOf(EURValue));
                    break;

                case ("USD") :
//TODO Round off to 3 decimal places (adjust as needed)
                    BigDecimal USDValue = new BigDecimal(getSGDValue).setScale(USD, RoundingMode.HALF_UP);
                    Locator usdInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                    usdInputLocator.fill(String.valueOf(USDValue));
                    break;

                case ("INR") :
//TODO Round off to 2 decimal places (adjust as needed)
                    BigDecimal INRValue = new BigDecimal(getSGDValue).setScale(INR, RoundingMode.HALF_UP);
                    Locator inrIputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                    inrIputLocator.fill(String.valueOf(INRValue));
                    break;

                default :
                    System.out.println("Not a valid currency code");
                    break;
            }

//TODO Invoice Document
            Locator invoiceDocumentButton = page.locator(DOCUMENT_ID);
            invoiceDocumentButton.first();
            invoiceDocumentButton.setInputFiles(Paths.get(INVOICE_DOCUMENT_PATH));

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();
        } else {
//TODO Invoice Document
            Locator invoiceDocumentButton = page.locator(DOCUMENT_ID);
            invoiceDocumentButton.first();
            invoiceDocumentButton.setInputFiles(Paths.get(INVOICE_DOCUMENT_PATH));

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Create", page);

            iLogout.performLogout();
            }
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice SGD Enable function: {}", exception.getMessage());
        }
    }
}