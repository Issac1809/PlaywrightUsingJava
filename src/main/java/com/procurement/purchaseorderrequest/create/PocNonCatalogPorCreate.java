package com.procurement.purchaseorderrequest.create;
import com.interfaces.PorCreateNonCatalog;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class PocNonCatalogPorCreate implements PorCreateNonCatalog {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocNonCatalogPorCreate(){
    }

//TODO Constructor
    public PocNonCatalogPorCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void BuyerPORCreate() {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("//a[contains(text(),' Create POR ')]").first().click();
    }

    public void Justification(){
        page.locator("#below5L").click();
    }

    public void TaxCode(){
        page.getByText("-- Select Tax Codes --").last().click();
        String taxCode = properties.getProperty("TaxCode");
        page.locator("//li[contains(text(),'"+ taxCode +"')]").click();
    }

    public void PORNotes() {
        page.locator("#notes").fill(properties.getProperty("PorNotes"));
    }

    public void PORCreate(){
        page.locator("#btnCreate").click();
        page.locator("//button[contains(text(),'Yes')]").click();
        logoutPageInterface.LogoutMethod();
    }
}