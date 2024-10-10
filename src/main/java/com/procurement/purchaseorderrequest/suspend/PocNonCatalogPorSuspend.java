package com.procurement.purchaseorderrequest.suspend;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class PocNonCatalogPorSuspend {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocNonCatalogPorSuspend(){
    }

//TODO Constructor
    public PocNonCatalogPorSuspend(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void PocNonCatalogPorSuspendMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnToSuspendPOR").click();
        page.locator(".bootbox-input").fill("Suspended");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}
