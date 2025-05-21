package com.source.classes.invoices.poinvoice.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
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
import static com.utils.saveReferenceIdUtil.saveReferenceIdFromResponse;

public class InvCreate implements IInvCreate {

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
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int invoiceTypeHandler(String type) {
        int status = 0;
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            boolean advancePaymentFlag = jsonNode.get("purchaseOrderRequests").get("advancePaymentFlag").asBoolean();
            boolean milestonePaymentFlag = jsonNode.get("purchaseOrderRequests").get("milestonePaymentFlag").asBoolean();
            int milestoneCount = jsonNode.get("purchaseOrderRequests").get("milestonePaymentCount").asInt();

            String transactionNumber = "";
            String psTransactionNumber = jsonNode.get("requisition").get("psTransactionNumber").asText();
            String salesTransactionNumber = jsonNode.get("requisition").get("salesTransactionNumber").asText();
            String sdTransactionNumber = jsonNode.get("requisition").get("sdTransactionNumber").asText();
            if(type.equalsIgnoreCase("PS")){
                transactionNumber = psTransactionNumber;
            } else if(type.equalsIgnoreCase("Sales")) {
                transactionNumber = salesTransactionNumber;
            } else if(type.equalsIgnoreCase("SD")){
                transactionNumber = sdTransactionNumber;
            }

            if (!advancePaymentFlag && !milestonePaymentFlag) {
                create(transactionNumber);
                double finalGSTPercentage = gst();
                ifSgdEnable(finalGSTPercentage, transactionNumber, advancePaymentFlag, milestonePaymentFlag);
                status = invoiceCreate();
            } if (advancePaymentFlag) {
                create(transactionNumber);

                Locator advancePaymentAndMilesToneButton = page.locator(ADVANCEPAYMENT_AND_MILESTONEPAYMENT_BUTTON);
                advancePaymentAndMilesToneButton.click();

                Locator advancePaymentCheckbox = page.locator(ADVANCEPAYMENT_CHECKBOX);
                advancePaymentCheckbox.click();

                Locator submitButton = page.locator(SAVE_BUTTON);
                submitButton.click();

                double finalGSTPercentage = gst();
                ifSgdEnable(finalGSTPercentage, transactionNumber, advancePaymentFlag, milestonePaymentFlag);
                status = invoiceCreate();
            } if (milestonePaymentFlag) {
                create(transactionNumber);

                Locator advancePaymentAndMilesToneButton = page.locator(ADVANCEPAYMENT_AND_MILESTONEPAYMENT_BUTTON);
                advancePaymentAndMilesToneButton.click();

                for (int i = 1; i <= milestoneCount; i++) {
                    Locator milestonePaymentCheckbox = page.locator(MILESTONEPAYMENT_CHECKBOX + i);
                    milestonePaymentCheckbox.click();
                }

                Locator submitButton = page.locator(SAVE_BUTTON);
                submitButton.click();

                double finalGSTPercentage = gst();
                ifSgdEnable(finalGSTPercentage, transactionNumber, advancePaymentFlag, milestonePaymentFlag);
                status = invoiceCreate();
            }

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Invoice Handler Function: {}", exception.getMessage());
        }
        return status;
    }

    public void create(String transactionNumber) {
        try {
            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.first().click();

            Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
            invoiceSelectLocator.first().click();

            Locator selectCompanyLocator = page.locator(SELECT_COMPANY_LOCATOR);
            selectCompanyLocator.click();

            List<String> companyIds = page.locator(COMPANY_RESULTS_LIST).allTextContents();
            int getCompanyIdSize = companyIds.size();
            for (int i = 0; i < getCompanyIdSize; i++) {
                if (transactionNumber.startsWith(COMPANY_ID_2400)) {
                    Locator string2400Locator = page.locator(get2400Id());
                    string2400Locator.last().click();
                } else if (transactionNumber.startsWith(COMPANY_ID_5K00)) {
                    Locator string5K00Locator = page.locator(get5K00Id());
                    string5K00Locator.last().click();
                } else if (transactionNumber.startsWith(COMPANY_ID_2U00)) {
                    Locator string2U00Locator = page.locator(get2U00Id());
                    string2U00Locator.last().click();
                } else if (transactionNumber.startsWith(COMPANY_ID_2W00)) {
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
            int randomNumber = (int) (Math.random() * 1000);
            Locator invoiceNumberLocator = page.locator(INVOICE_NUMBER_LOCATOR);
            invoiceNumberLocator.fill(invoiceNumber + randomNumber);

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

            Thread.sleep(2000);

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

    public void ifSgdEnable(double finalGSTPercentage, String transactionNumber, boolean advancePaymentFlag, boolean milestonePaymentFlag) {
        try {
            String currencyCode = jsonNode.get("configSettings").get("currencyCode").asText();
            if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (transactionNumber.startsWith(COMPANY_ID_2400)
                    || transactionNumber.startsWith(COMPANY_ID_5K00) || transactionNumber.startsWith(COMPANY_ID_2U00) ||
                    transactionNumber.startsWith(COMPANY_ID_2W00))) {
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

    public int invoiceCreate() throws JsonProcessingException {
        int status = 0;
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

        String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
        // Locate the row containing the dynamic poReferenceId and click the <a> tag
        Locator rows = page.locator("#listContainer tr");
        int rowCount = rows.count();
        for (int i = 0; i < rowCount; i++) {
            Locator row = rows.nth(i);
            String referenceText = row.locator("td:nth-child(3)").innerText();
            if (referenceText.contains(poReferenceId)) {
                Response invoiceResponse = page.waitForResponse(
                        response -> response.url().startsWith(appUrl + "/api/VP/Invoices/") && response.status() == 200,
                        row.locator("a").first()::click
                );
                saveReferenceIdFromResponse(invoiceResponse, "invoice", "invoiceReferenceId");

                status = invoiceResponse.status();
                break;
            }
        }

        page.waitForLoadState(LoadState.NETWORKIDLE);

        PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Create", page);

        return status;
    }
}