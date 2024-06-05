package com.yokogawa.invoice;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.playwrightfactory.PlayWrightFactory;
import com.yokogawa.currencyexchangerate.CurrencyExchangeRate;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;

public class POInvoiceCreate implements POInvoiceCreateInterface {

    PlayWrightFactory playWrightFactory;
    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    CurrencyExchangeRate currencyExchangeRate;
    String poReferenceId = "5K0023PO02376";

    int EUR = 3;
    int USD = 2;
    int INR = 1;

    private POInvoiceCreate(){
    }

    public POInvoiceCreate(PlayWrightFactory playWrightFactory, LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, CurrencyExchangeRate currencyExchangeRate){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.currencyExchangeRate = currencyExchangeRate;
        this.playWrightFactory = playWrightFactory;
    }

    public void VendorCreatePOInvoice() {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//*[contains(text(), 'Invoices')]").click();
        page.locator(".btn.btn-primary").first().click();
        page.locator("#select2-companyId-container").click();
        List<String> companyId = page.locator("#select2-companyId-results").allTextContents();
        int getCompanyIdSize = companyId.size();
        for( int i = 0; i < getCompanyIdSize; i++){
            if(poReferenceId.contains("2400")){
                page.locator("//li[contains(text(), '2400')]").last().click();
            }
            if(companyId.get(i).contains("5K00")){
                page.locator("//li[contains(text(), '5K00')]").last().click();
            }
            if(companyId.get(i).contains("2U00")){
                page.locator("//li[contains(text(), '2U00')]").last().click();
            }
            if(companyId.get(i).contains("2W00")){
                page.locator("//li[contains(text(), '2W00')]").last().click();
            }
        }
        page.pause();
        page.locator("#select2-typeId-container").last().click();
        page.locator(".select2-search__field").fill("Purchase Order");
        page.locator("//li[contains(text(), 'Purchase Order')]").first().click();
        page.locator("#invoiceNumber").fill(properties.getProperty("InvoiceNumber"));
        page.getByPlaceholder("Select Invoice Date").last().click();
        page.locator("//span[@class='flatpickr-day today']").last().click();
        page.locator("#select2-poId-container").click();
        page.locator(".select2-search__field").fill(poReferenceId);
        page.locator("//li[contains(text(), '" + poReferenceId + "')]").first().click();
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

    public double VendorTotalGST() {
        double finalTotalGSTPercentage = 0;
        try {
            //TODO Used JavaScript to get the value of the input field => page.evaluate("document.getElementById('USDtotalGST').value");
            String TotalGSTPercentage = String.valueOf(page.evaluate("document.getElementById('USDtotalGST').value"));
            String getTotalGSTPercentage = TotalGSTPercentage.replaceAll("[^\\d.]", "");
            finalTotalGSTPercentage = Double.parseDouble(getTotalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the Error :" + error.getMessage());
        }
        return finalTotalGSTPercentage;
    }

    public void SGDEquivalentEnable(double finalTotalGSTPercentage, double finalGSTPercentage) {
        String currencyCode = properties.getProperty("InvoiceCurrencyCode");
        if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (poReferenceId.startsWith("2400") || poReferenceId.startsWith("5K00") || poReferenceId.startsWith("2U00") || poReferenceId.startsWith("2W00"))) {
//TODO Foreign Sub-Total
            String getForeignSubTotal = page.locator("#USDsubtotal").getAttribute("value");
            String foreignSubTotal = getForeignSubTotal.replaceAll("[^\\d.]", "");
            double finalforeignSubTotal = Double.parseDouble(foreignSubTotal);
//TODO Input Sub-Total
            double inputSubTotal = 99999999;
            Locator finalInputSubTotal = page.locator("#SGDsubtotalInput");
            finalInputSubTotal.fill(String.valueOf(inputSubTotal));
//TODO Currency Exchange Rate
            String totalCurrencyExchangeRate = String.valueOf(inputSubTotal / finalforeignSubTotal);
            String replaceTotalCurrencyExchangeRate = totalCurrencyExchangeRate.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
            double value = Double.parseDouble(replaceTotalCurrencyExchangeRate);
//TODO Round off to 5 decimal places
            BigDecimal roundedValue = new BigDecimal(value).setScale(5, RoundingMode.HALF_UP);
            double totalRoundedValue = Double.parseDouble(String.valueOf(roundedValue));
            double finalTotalRoundedValue = totalRoundedValue * finalTotalGSTPercentage;

            if (currencyCode.equals("EUR")) {
                String sgdTotalGST = String.valueOf(finalTotalRoundedValue);
//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);
//TODO Round off to 5 decimal places (adjust as needed)
                BigDecimal CADValue = new BigDecimal(getSGDValue).setScale(EUR, RoundingMode.HALF_UP);
                page.locator("#SGDtotalGSTInput").fill(String.valueOf(CADValue));
            }
            if (currencyCode.equals("USD")) {
                String sgdTotalGST = String.valueOf(finalTotalRoundedValue);
//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);
//TODO Round off to 5 decimal places (adjust as needed)
                BigDecimal USDValue = new BigDecimal(getSGDValue).setScale(USD, RoundingMode.HALF_UP);
                page.locator("#SGDtotalGSTInput").fill(String.valueOf(USDValue));
            }
            if (currencyCode.equals("INR")) {
                String sgdTotalGST = String.valueOf(finalTotalRoundedValue);
//TODO Keep only digits and the decimal point
                String replaceSGDTotalGST = sgdTotalGST.replaceAll("[^\\d.]", "");
//TODO Convert to double for rounding
                double getSGDValue = Double.parseDouble(replaceSGDTotalGST);
//TODO Round off to 5 decimal places (adjust as needed)
                BigDecimal INRValue = new BigDecimal(getSGDValue).setScale(INR, RoundingMode.HALF_UP);
                page.locator("#SGDtotalGSTInput").fill(String.valueOf(INRValue));
            }
            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
        } else {
            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
        }
    }
}