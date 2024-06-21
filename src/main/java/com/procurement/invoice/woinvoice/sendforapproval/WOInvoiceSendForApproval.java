package com.procurement.invoice.woinvoice.sendforapproval;

import com.interfaces.*;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceSendForApproval implements WOSendForApprovalInterface {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WOInvoiceSendForApproval(){
    }

//TODO Constructor
    public WOInvoiceSendForApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void SendForApproval(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceTable = page.locator("#listContainer tr td").allTextContents();
        for (String tr : invoiceTable){
            if (tr.contains(woReferenceId)) {
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnSendApproval").click();
        page.locator(".btn btn-primary bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}