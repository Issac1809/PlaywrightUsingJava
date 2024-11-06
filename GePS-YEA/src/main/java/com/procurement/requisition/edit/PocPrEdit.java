package com.procurement.requisition.edit;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.pr.PrApprove;
import com.interfaces.pr.PrAssign;
import com.interfaces.pr.PrEdit;
import com.microsoft.playwright.Page;
import com.interfaces.pr.PrSendForApproval;
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

    public void PrEditMethod() {
        try {
        loginPageInterface.LoginMethod();
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        page.locator("#btnEdit").click();
        Thread.sleep(2000);
        page.locator("#btnUpdate").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PrRejectEdit()  {
        try {
        PrEditMethod();
        prSendForApproval.NonCatalogPrSendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PrBuyerManagerSuspendEdit()  {
        try {
        PrEditMethod();
        prSendForApproval.NonCatalogPrSendForApproval();
        prApprove.Approve();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void PrBuyerSuspendEdit()  {
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
