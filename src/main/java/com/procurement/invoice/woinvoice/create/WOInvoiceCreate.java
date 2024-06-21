package com.procurement.invoice.woinvoice.create;

import com.factory.PlayWrightFactory;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.WOInvoiceCreateInterface;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.currencyexchangerate.CurrencyExchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class WOInvoiceCreate implements WOInvoiceCreateInterface {

    PlayWrightFactory playWrightFactory;
    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    CurrencyExchangeRate currencyExchangeRate;

    int EUR = 4;
    int USD = 3;
    int INR = 2;

    private WOInvoiceCreate() {
    }

//TODO Constructor
    public WOInvoiceCreate(PlayWrightFactory playWrightFactory, LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, CurrencyExchangeRate currencyExchangeRate) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.currencyExchangeRate = currencyExchangeRate;
        this.playWrightFactory = playWrightFactory;
    }

    public void VendorCreateWOInvoice() {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//*[contains(text(), 'Invoices')]").click();
        page.locator(".btn.btn-primary").first().click();
        page.locator("#select2-companyId-container").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> companyId = page.locator("#select2-companyId-results").allTextContents();
        int getCompanyIdSize = companyId.size();
        for (int i = 0; i < getCompanyIdSize; i++) {
            if (woReferenceId.contains("2400")) {
                page.locator("//li[contains(text(), '2400')]").last().click();
            }
            if (woReferenceId.contains("5K00")) {
                page.locator("//li[contains(text(), '5K00')]").last().click();
            }
            if (woReferenceId.contains("2U00")) {
                page.locator("//li[contains(text(), '2U00')]").last().click();
            }
            if (woReferenceId.contains("2W00")) {
                page.locator("//li[contains(text(), '2W00')]").last().click();
            }
        }
        page.locator("#select2-typeId-container").last().click();
        page.locator(".select2-search__field").fill("Purchase Order");
        page.locator("//li[contains(text(), 'Purchase Order')]").first().click();
        page.locator("#invoiceNumber").fill(properties.getProperty("InvoiceNumber"));
        page.getByPlaceholder("Select Invoice Date").last().click();
        page.locator("//span[@class='flatpickr-day today']").last().click();
        page.locator("#select2-poId-container").click();
        page.locator(".select2-search__field").fill(woReferenceId);
        page.locator("//li[contains(text(), '" + woReferenceId + "')]").first().click();
        String getCurrencyCode = page.locator("#currencyCode").textContent();
        playWrightFactory.savePropertiesToFile2(getCurrencyCode);
    }

    public double VendorGST() {
//TODO Used JavaScript to get the value of the input field => page.evaluate("document.getElementById('USDgstId').value");
        double finalGSTPercentage = 0;
        try {
            String GSTPercentage = String.valueOf(page.locator("#USDgstId").evaluate("document.getElementById('USDgstId').value"));
            String getGSTPercentage = GSTPercentage.replaceAll("[^\\d.]", "");
            finalGSTPercentage = Double.parseDouble(getGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the Error :" + error.getMessage());
        }
        return finalGSTPercentage;
    }

    public void SGDEquivalentEnable(double finalGSTPercentage) {
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        String currencyCode = properties.getProperty("InvoiceCurrencyCode");
        if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (woReferenceId.startsWith("2400") || woReferenceId.startsWith("5K00") || woReferenceId.startsWith("2U00") || woReferenceId.startsWith("2W00"))) {
//TODO Foreign Sub-Total
            String getForeignSubTotal = page.locator("#USDsubtotal").getAttribute("value");
            String foreignSubTotal = getForeignSubTotal.replaceAll("[^\\d.]", "");
            double finalforeignSubTotal = Double.parseDouble(foreignSubTotal);
//TODO Input Sub-Total
            double getCurrencyExchangeRate = currencyExchangeRate.findCurrency();
            double sgdEquivalentSubTotal = finalforeignSubTotal * getCurrencyExchangeRate;
            page.locator("#SGDsubtotalInput").fill(String.valueOf(sgdEquivalentSubTotal));
//TODO Manually trigger the input and change events to ensure JavaScript logic executes
            page.locator("#SGDsubtotalInput").evaluate("el => { el.dispatchEvent(new Event('keyup', { bubbles: true })); }");
            //TODO Currency Exchange Rate
            double totalCurrencyExchangeRate = sgdEquivalentSubTotal / finalforeignSubTotal;
//TODO Currency Exchange Rate * Total GST
            String getForeignTotalGst = page.locator("#USDtotalGST").getAttribute("value");
            String foreignTotalGst = getForeignTotalGst.replaceAll("[^\\d.]", "");
            double finalForeignTotalGst = Double.parseDouble(foreignTotalGst);
            double inputTotalGst = totalCurrencyExchangeRate * finalForeignTotalGst;

            if (currencyCode.equals("EUR")) {
                String sgdTotalGST = String.valueOf(inputTotalGst);
//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);
//TODO Round off to 4 decimal places (adjust as needed)
                BigDecimal EURValue = new BigDecimal(getSGDValue).setScale(EUR, RoundingMode.HALF_UP);
                page.locator("#SGDtotalGSTInput").fill(String.valueOf(EURValue));
            }

            if (currencyCode.equals("USD")) {
                String sgdTotalGST = String.valueOf(inputTotalGst);
//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);
//TODO Round off to 3 decimal places (adjust as needed)
                BigDecimal USDValue = new BigDecimal(getSGDValue).setScale(USD, RoundingMode.HALF_UP);
                page.locator("#SGDtotalGSTInput").fill(String.valueOf(USDValue));
            }

            if (currencyCode.equals("INR")) {
                String sgdTotalGST = String.valueOf(inputTotalGst);
//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);
//TODO Round off to 2 decimal places (adjust as needed)
                BigDecimal INRValue = new BigDecimal(getSGDValue).setScale(INR, RoundingMode.HALF_UP);
                page.locator("#SGDtotalGSTInput").fill(String.valueOf(INRValue));
            }

//TODO Invoice Document
            Locator invoiceDocumentButton = page.locator("#doc1").first();
            invoiceDocumentButton.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Invoice Document.xlsx"));
            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
        } else {
//TODO Invoice Document
            Locator invoiceDocumentButton2 = page.locator("#doc1").first();
            invoiceDocumentButton2.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Invoice Documents.xlsx"));
            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
        }
    }
}