package com.yokogawa.invoice;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

public class POInvoiceCreate implements POInvoiceCreateInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    int CAD = 3;
    int USD = 2;
    int INR = 1;
    public void VendorCreatePOInvoice(String mailId, String poReferenceId, String InvoiceNumber, Page page) {
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Invoices')]").click();
        page.locator(".btn.btn-primary").first().click();
        page.locator("#select2-typeId-container").last().click();
        page.locator(".select2-search__field").fill("Purchase Order");
        page.locator("//li[contains(text(), 'Purchase Order')]").first().click();
//TODO
        page.locator("#invoiceNumber").fill(InvoiceNumber);
        page.getByPlaceholder("Select Invoice Date").last().click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#select2-poId-container").click();
        page.locator(".select2-search__field").fill(poReferenceId);
        page.locator("//li[contains(text(), '"+ poReferenceId +"')]").first().click();
        String currencyCode = page.locator("#currencyCode").textContent();
        int GSTPercentage = Integer.parseInt(page.locator("#currencyCode").getAttribute("value"));
        if(!currencyCode.equals("SGD") && GSTPercentage != 0 && (poReferenceId.startsWith("2Q00") || poReferenceId.startsWith("5K00"))) {
            page.locator("#SGDsubtotalInput").fill(String.valueOf(100));
            double getCurrencyExchangeRate = Double.parseDouble(page.locator("#currencyExchangeRateId").getAttribute("value"));
            double getTotalGST = Double.parseDouble(page.locator("#USDtotalGST").getAttribute("value"));
            double SGDEquivalentforGSTPurpose = getCurrencyExchangeRate * getTotalGST;

            page.locator("#SGDtotalGSTInput").fill(String.valueOf(SGDEquivalentforGSTPurpose));
        }
        page.locator("#btnSubmit").click();
        page.locator(".bootbox-accept").click();
        }
    }
