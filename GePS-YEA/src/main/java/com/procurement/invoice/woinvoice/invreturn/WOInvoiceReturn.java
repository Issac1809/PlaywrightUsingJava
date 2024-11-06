package com.procurement.invoice.woinvoice.invreturn;

import com.interfaces.inv.woinv.WoInvReturn;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.wo.WOSendForApprovalInterface;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceReturn implements WoInvReturn {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    WOSendForApprovalInterface woSendForApprovalInterface;

    private WOInvoiceReturn(){
    }

    //TODO Constructor
    public WOInvoiceReturn(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, WOSendForApprovalInterface woSendForApprovalInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.woSendForApprovalInterface = woSendForApprovalInterface;
    }

    public void WOInvoiceReturnMethod(){
        try {
        woSendForApprovalInterface.SendForApproval();
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnToSuspendInvoice").click();
        page.locator(".bootbox-input").fill("Cancelled");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
