package org.purchaseorderrequest.create;
import com.microsoft.playwright.Page;

public class PocCatalogPorCreate implements PorCreateCatalog {
    public void BuyerLogin(String mailId, String password, Page page) {
//TODO Login Page
        page.locator("Input_Email").fill(mailId);
        page.locator("Input_Password").fill(password);
        page.locator("login-submit").click();
    }
    public void BuyerPORCreate(String title, Page page) {
        page.locator("//*[contains(text(),'"+ title +"')]").click();
        page.locator("btnCreatePOR").click();
    }
    public void TaxCode(Page page){
        page.getByText("-- Select Tax Codes --").click();
        page.locator("//*[contains(text(),'PG-8% Foreign currency standard rate purchase')]").click();
    }
    public void PORNotes(String notes, Page page) {
        page.locator("notes").fill(notes);
    }
    public void PORCreate(Page page) {
        page.locator("btnCreate").click();
        page.locator("//button[contains(text(),'Yes')]").click();
        page.close();
    }
}
