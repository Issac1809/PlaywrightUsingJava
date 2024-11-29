package com.poc.classes.invoice.woinvoice.create;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.currencyexchangerate.ICurrencyExchangeRate;
import com.poc.interfaces.invoices.woinvoices.IWoInvCreate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import static com.constants.invoices.woinvoice.LInvCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvCreate implements IWoInvCreate {

    PlaywrightFactory playwrightFactory;
    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    ICurrencyExchangeRate iCurrencyExchangeRate;

    int EUR = 4;
    int USD = 3;
    int INR = 2;

    private WoInvCreate() {
    }

//TODO Constructor
    public WoInvCreate(PlaywrightFactory playwrightFactory, ILogin iLogin, Properties properties, Page page, ILogout iLogout, ICurrencyExchangeRate iCurrencyExchangeRate) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iCurrencyExchangeRate = iCurrencyExchangeRate;
        this.playwrightFactory = playwrightFactory;
    }

    public void create() {
        try {
            String vendorMailId = properties.getProperty("VendorMailId");
            iLogin.performLogin(vendorMailId);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            waitForLocator(invoiceNavigationBarLocator);
            invoiceNavigationBarLocator.click();

            Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
            waitForLocator(invoiceSelectLocator);
            invoiceSelectLocator.first().click();

            Locator selectCompanyLocator = page.locator(SELECT_COMPANY_LOCATOR);
            waitForLocator(selectCompanyLocator);
            selectCompanyLocator.click();

            String woReferenceId = properties.getProperty("WoReferenceId");
            List<String> companyIds = page.locator(COMPANY_RESULTS_LIST).allTextContents();
            int getCompanyIdSize = companyIds.size();
            for (int i = 0; i < getCompanyIdSize; i++) {
                if (woReferenceId.startsWith(COMPANY_ID_2400)) {
                    Locator string2400Locator = page.locator(get2400Id());
                    waitForLocator(string2400Locator);
                    string2400Locator.last().click();
                }
                else if (woReferenceId.startsWith(COMPANY_ID_5K00)) {
                    Locator string5K00Locator = page.locator(get5K00Id());
                    waitForLocator(string5K00Locator);
                    string5K00Locator.last().click();
                }
                else if (woReferenceId.startsWith(COMPANY_ID_2U00)) {
                    Locator string2U00Locator = page.locator(get2U00Id());
                    waitForLocator(string2U00Locator);
                    string2U00Locator.last().click();
                }
                else if (woReferenceId.startsWith(COMPANY_ID_2W00)) {
                    Locator string2W00Locator = page.locator(get2W00Id());
                    waitForLocator(string2W00Locator);
                    string2W00Locator.last().click();
                }
            }

            Locator selectTypeLocator = page.locator(SELECT_TYPE);
            waitForLocator(selectTypeLocator);
            selectTypeLocator.last().click();

            Locator searchFieldLocator1 = page.locator(SEARCH_FIELD);
            waitForLocator(searchFieldLocator1);
            searchFieldLocator1.fill("Work Order");

            Locator poSelectLocator = page.locator(PO_LOCATOR);
            waitForLocator(poSelectLocator);
            poSelectLocator.first().click();

            String invoiceNumber = properties.getProperty("InvoiceNumber");
            Locator invoiceNumberLocator = page.locator(INVOICE_NUMBER_LOCATOR);
            waitForLocator(invoiceNumberLocator);
            invoiceNumberLocator.fill(invoiceNumber);

            Locator invoiceDateLocator = page.getByPlaceholder(DATE_PLACE_HOLDER);
            waitForLocator(invoiceDateLocator);
            invoiceDateLocator.last().click();

            Locator todayLocator = page.locator(TODAY);
            waitForLocator(todayLocator);
            todayLocator.last().click();

            Locator poNumberinputLocator = page.locator(PO_NUMBER_INPUT);
            waitForLocator(poNumberinputLocator);
            poNumberinputLocator.click();

            Locator searchFieldLocator2 = page.locator(SEARCH_FIELD);
            waitForLocator(searchFieldLocator2);
            searchFieldLocator2.fill(woReferenceId);

            Locator selectPoLocator = page.locator(getWoReferenceId(woReferenceId));
            waitForLocator(selectPoLocator);
            selectPoLocator.first().click();

            Locator currencyCodeLocator = page.locator(CURRENCY_CODE);
            waitForLocator(currencyCodeLocator);
            String getCurrencyCode = currencyCodeLocator.textContent();

            playwrightFactory.saveToPropertiesFile("Currency Code", getCurrencyCode);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public double gst() {
//TODO Used JavaScript to get the value of the input field => page.evaluate("document.getElementById('USDgstId').value");
        double finalGSTPercentage = 0;
        try {
            Locator gstPercentageLocator = page.locator(GST_LOCATOR);
            waitForLocator(gstPercentageLocator);
            String getGstPercentage = String.valueOf(gstPercentageLocator.evaluate(DOM_TRIGGER));
            String gstPercentage = getGstPercentage.replaceAll("[^\\d.]", "");
            finalGSTPercentage = Double.parseDouble(gstPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
        return finalGSTPercentage;
    }

    public void ifSgdEnable(double finalGSTPercentage) {
        try {
            String woReferenceId = properties.getProperty("WoReferenceId");
            String currencyCode = properties.getProperty("InvoiceCurrencyCode");
            if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (woReferenceId.startsWith(COMPANY_ID_2400) || woReferenceId.startsWith(COMPANY_ID_5K00) || woReferenceId.startsWith(COMPANY_ID_2U00) || woReferenceId.startsWith(COMPANY_ID_2W00))) {

//TODO Foreign Sub-Total
                Locator foreignSubTotalLocator = page.locator(GST_LOCATOR);
                waitForLocator(foreignSubTotalLocator);
                String foreignSubTotal = foreignSubTotalLocator.getAttribute("value");
                String getForeignSubTotal = foreignSubTotal.replaceAll("[^\\d.]", "");
                double finalForeignSubTotal = Double.parseDouble(getForeignSubTotal);

//TODO Input Sub-Total
                double currencyExchangeRate = iCurrencyExchangeRate.findCurrency();
                double sgdEquivalentSubTotal = finalForeignSubTotal * currencyExchangeRate;

                Locator sgdInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                waitForLocator(sgdInputLocator);
                sgdInputLocator.fill(String.valueOf(sgdEquivalentSubTotal));

//TODO Manually trigger the input and change events to ensure JavaScript logic executes
                Locator triggerSGDInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                waitForLocator(triggerSGDInputLocator);
                triggerSGDInputLocator.evaluate(DOM_TRIGGER_SGD_INPUT);

//TODO Currency Exchange Rate
                double totalCurrencyExchangeRate = sgdEquivalentSubTotal / finalForeignSubTotal;

//TODO Currency Exchange Rate * Total GST
                Locator foreignTotalGst = page.locator(GST_LOCATOR);
                waitForLocator(foreignSubTotalLocator);
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
                        waitForLocator(eurInputLocator);
                        eurInputLocator.fill(String.valueOf(EURValue));
                        break;

                    case ("USD") :
//TODO Round off to 3 decimal places (adjust as needed)
                        BigDecimal USDValue = new BigDecimal(getSGDValue).setScale(USD, RoundingMode.HALF_UP);
                        Locator usdInputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                        waitForLocator(usdInputLocator);
                        usdInputLocator.fill(String.valueOf(USDValue));
                        break;

                    case ("INR") :
//TODO Round off to 2 decimal places (adjust as needed)
                        BigDecimal INRValue = new BigDecimal(getSGDValue).setScale(INR, RoundingMode.HALF_UP);
                        Locator inrIputLocator = page.locator(SGD_SUB_TOTAL_INPUT);
                        waitForLocator(inrIputLocator);
                        inrIputLocator.fill(String.valueOf(INRValue));
                        break;

                    default :
                        System.out.println("Not a valid currency code");
                        break;
                }

//TODO Invoice Document
                Locator invoiceDocumentButton = page.locator(DOCUMENT_ID);
                waitForLocator(invoiceDocumentButton);
                invoiceDocumentButton.first();
                invoiceDocumentButton.setInputFiles(Paths.get(INVOICE_DOCUMENT_PATH));

                Locator createButtonLocator = page.locator(CREATE_BUTTON);
                waitForLocator(createButtonLocator);
                createButtonLocator.click();

                Locator acceptLocator = page.locator(ACCEPT_BUTTON);
                waitForLocator(acceptLocator);
                acceptLocator.click();
            } else {
//TODO Invoice Document
                Locator invoiceDocumentButton = page.locator(DOCUMENT_ID);
                waitForLocator(invoiceDocumentButton);
                invoiceDocumentButton.first();
                invoiceDocumentButton.setInputFiles(Paths.get(INVOICE_DOCUMENT_PATH));

                Locator createButtonLocator = page.locator(CREATE_BUTTON);
                waitForLocator(createButtonLocator);
                createButtonLocator.click();

                Locator acceptLocator = page.locator(ACCEPT_BUTTON);
                waitForLocator(acceptLocator);
                acceptLocator.click();
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}