package com.yokogawa.purchaseorderrequest.create;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
public class PocCatalogPorCreate implements PorCreateCatalog {
    Login login = new LoginPage();
    public void BuyerLogin(String mailId, Page page) {
        login.Login(mailId, page);
    }
    public void BuyerPORCreate(String title, Page page) {
        page.locator("//*[contains(text(),'"+ title +"')]").first().click();
        page.locator("#btnCreatePOR").click();
    }
    public void TaxCode(String taxCode, Page page){
        page.getByText("-- Select Tax Codes --").click();
        page.locator("//li[contains(text(),'"+ taxCode +"')]").click();
    }
    public void PORNotes(String notes, Page page) {
        page.locator("#notes").fill(notes);
    }
    public void PORCreate(Page page) {
        page.locator("#btnCreate").click();
        page.locator("//button[contains(text(),'Yes')]").click();
    }
}
