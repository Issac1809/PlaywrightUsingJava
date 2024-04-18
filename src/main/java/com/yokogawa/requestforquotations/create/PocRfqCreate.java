package com.yokogawa.requestforquotations.create;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

public class PocRfqCreate implements RfqCreate {
    Login login = new LoginPage();
    public void BuyerLogin(String mailId, Page page) {
        login.Login(mailId, page);
    }
    public void BuyerRfqCreate(String title, Page page) {
        page.locator("//*[contains(text(),'"+ title +"')]").first().click();
        page.locator("#btnCreateRFQ").click();
    }
    public void RfQNotes(String notes, Page page) {
        page.locator("#notes").fill(notes);
    }
    public void RFQCreate(Page page) throws InterruptedException {
        Thread.sleep(1000);
        page.locator("#btnCreate").click();
        page.locator("//button[contains(text(),'Yes')]").click();
    }
}
