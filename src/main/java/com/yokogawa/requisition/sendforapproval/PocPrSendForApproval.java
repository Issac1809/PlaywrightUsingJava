package com.yokogawa.requisition.sendforapproval;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import java.util.Properties;

public class PocPrSendForApproval implements PrSendForApproval {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocPrSendForApproval(){
    }

    public PocPrSendForApproval(Page page, LogoutPageInterface logoutPageInterface, LoginPageInterface loginPageInterface){
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

//TODO Test Constructor
    public PocPrSendForApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void NonCatalogPrSendForApproval() {
        String title = properties.getProperty("Title");
        loginPageInterface.LoginMethod();
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.locator("#btnSendApproval").click();
        page.locator("//button[contains(text(), 'Yes')]").click();
        logoutPageInterface.LogoutMethod();
    }
}