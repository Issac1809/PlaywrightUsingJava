package com.yokogawa.invoice;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.List;

public class POInvoiceCreate {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void VendorCreatePOInvoice(String mailId, String poReferenceId, Page page) {
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Invoices')]").click();
        page.locator(".btn.btn-primary").first().click();
        page.locator("#select2-typeId-container").last().click();
        page.locator(".select2-search__field").fill("Purchase Order");
        page.locator("//li[contains(text(), 'Purchase Order')]").first().click();
//TODO
        page.locator("#invoiceNumber").fill("INV-001");
        page.getByPlaceholder("Select Invoice Date").last().click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#select2-poId-container").click();
        page.locator(".select2-search__field").fill(poReferenceId);
        page.locator("//li[contains(text(), '"+ poReferenceId +"')]").first().click();
        String currencyCode = page.locator("#currencyCode").textContent();
        if(!currencyCode.equals("SGD")) {
            page.locator("#SGDsubtotalInput").fill(String.valueOf(1));

        }
        page.locator("#btnSubmit").click();
        page.locator(".bootbox-accept").click();
        }
    }
