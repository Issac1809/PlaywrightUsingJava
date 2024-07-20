package com.procurement.requisition.suspend;
import com.interfaces.pr.PrSuspend;
import com.microsoft.playwright.Page;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.pr.PrEdit;
import java.util.Properties;

public class PocPrBuyerManagerSuspend implements PrSuspend {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;
    PrEdit prEdit;

    private PocPrBuyerManagerSuspend(){
    }

//TODO Constructor
    public PocPrBuyerManagerSuspend(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PrEdit prEdit){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.prEdit = prEdit;
    }

    public void Suspend() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("BuyerManager"));
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.locator("#btnSuspend").click();
        page.locator(".bootbox-input").fill("Buyer Manager Suspended");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        prEdit.PrBuyerManagerSuspendEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}