package com.yokogawa.invoice;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

public class POInvoiceCreate implements POInvoiceCreateInterface {
    Login login = new LoginPage();
    int EUR = 3;
    int USD = 2;
    int INR = 1;

    public String VendorCreatePOInvoice(String mailId, String poReferenceId, String InvoiceNumber, Page page) {
        login.Login(mailId, page);
        page.pause();
        page.locator("//*[contains(text(), 'Invoices')]").click();
        page.locator(".btn.btn-primary").first().click();
        page.locator("#select2-typeId-container").last().click();
        page.locator(".select2-search__field").fill("Purchase Order");
        page.locator("//li[contains(text(), 'Purchase Order')]").first().click();
//TODO
        page.locator("#invoiceNumber").fill(InvoiceNumber);
        page.getByPlaceholder("Select Invoice Date").last().click();
        page.locator("//span[@class='flatpickr-day today']").last().click();
        page.locator("#select2-poId-container").click();
        page.locator(".select2-search__field").fill(poReferenceId);
        page.locator("//li[contains(text(), '" + poReferenceId + "')]").first().click();
        String currencyCode = page.locator("#currencyCode").textContent();
        return currencyCode;
    }
    public double VendorGST(Page page) {
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

    public double VendorTotalGST(Page page) {
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
    public void SGDEquivalentEnable(String currencyCode, double finalTotalGSTPercentage, double finalGSTPercentage, String poReferenceId, Page page) {
        if (!currencyCode.equals("SGD") && finalGSTPercentage != 0 && (poReferenceId.startsWith("2400") || poReferenceId.startsWith("5K00"))) {
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
//TODO Invoice Documents
//            List<String> invoiceDocuments = page.locator("#tblAttachments thead tbody tr td").all();
//            System.out.println(invoiceDocuments);
//            String getMandatoryDocument = String.valueOf(page.locator("//*[@class='required']"));
//            System.out.println(getMandatoryDocument);
//            for(int i = 0; i < invoiceDocuments.size(); i++){
//                System.out.println(invoiceDocuments.size());
//                System.out.println(invoiceDocuments.get(i));
//                if(invoiceDocuments.get(i).contains(getMandatoryDocument)){
//                    Locator invoiceDocument = page.locator("#doc" + invoiceDocuments.get(i));
//                    invoiceDocument.setInputFiles(Paths.get("./Downloads/Commercial Documents.xlsx"));
//                }
//            }



            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
        } else {
            page.locator("#btnCreate").click();
            page.locator(".bootbox-accept").click();
        }
    }
}