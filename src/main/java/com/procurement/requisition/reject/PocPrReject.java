package com.procurement.requisition.reject;
import com.interfaces.PrReject;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PrEdit;
import java.util.Properties;

public class PocPrReject implements PrReject {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;
    PrEdit prEdit;

    private PocPrReject(){
    }

//TODO Constructor
    public PocPrReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PrEdit prEdit){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.prEdit = prEdit;
    }

    public void Reject() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("ProjectManager"));
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.waitForSelector("#btnReject").click();
        page.waitForSelector(".bootbox-input").fill("Rejected");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        prEdit.PrRejectEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}