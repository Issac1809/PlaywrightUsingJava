package com.procurement.purchaseorderrequest.edit;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PorEdit;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class PocPorEdit implements PorEdit {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocPorEdit(){
    }

//TODO Constructor
    public PocPorEdit(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void PorEditMethod() throws InterruptedException {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnEdit").click();
        Thread.sleep(2000);
        page.locator("#btnUpdate").click();
        page.locator(".bootbox-input").fill("Updated");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}