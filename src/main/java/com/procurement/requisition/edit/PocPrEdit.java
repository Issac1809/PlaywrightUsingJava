package com.procurement.requisition.edit;
import com.interfaces.*;
import com.microsoft.playwright.Page;
import com.interfaces.PrSendForApproval;
import java.util.Properties;

public class PocPrEdit implements PrEdit {

    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    Properties properties;
    Page page;
    PrSendForApproval prSendForApproval;
    PrApprove prApprove;
    PrAssign prAssign;

    private PocPrEdit(){
    }

//TODO Constructor
    public PocPrEdit(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PrSendForApproval prSendForApproval, PrApprove prApprove, PrAssign prAssign){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.prSendForApproval = prSendForApproval;
        this.prApprove = prApprove;
        this.prAssign = prAssign;
    }

    public void PrEditMethod() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod();
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.waitForSelector("#btnEdit").click();
        Thread.sleep(2000);
        page.waitForSelector("#btnUpdate").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PrRejectEdit() throws InterruptedException {
        try {
        PrEditMethod();
        prSendForApproval.NonCatalogPrSendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PrBuyerManagerSuspendEdit() throws InterruptedException {
        try {
        PrEditMethod();
        prSendForApproval.NonCatalogPrSendForApproval();
        prApprove.Approve();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PrBuyerSuspendEdit() throws InterruptedException {
        try {
        PrEditMethod();
        prSendForApproval.NonCatalogPrSendForApproval();
        prApprove.Approve();
        prAssign.BuyerManagerLogin();
        prAssign.BuyerManagerAssign();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
