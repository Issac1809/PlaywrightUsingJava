package com.procurement.requisition.sendforapproval;
import com.interfaces.pr.PrSendForApproval;
import com.microsoft.playwright.Page;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import java.util.Properties;

public class PocPrSendForApproval implements PrSendForApproval {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocPrSendForApproval() {
    }

    //TODO Constructor
    public PocPrSendForApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface) {
        this.properties = properties;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void NonCatalogPrSendForApproval() {
        try {
        String title = properties.getProperty("Title");
        loginPageInterface.LoginMethod();
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.locator("#btnSendApproval").click();
        page.locator("//button[contains(text(), 'Yes')]").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}