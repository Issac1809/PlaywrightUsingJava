package com.procurement.invoice.woinvoice.reject;

import com.interfaces.*;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceReject implements WoInvReject {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    WOSendForApprovalInterface woSendForApprovalInterface;

    private WOInvoiceReject(){
    }

//TODO Constructor
    public WOInvoiceReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, WOSendForApprovalInterface woSendForApprovalInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.woSendForApprovalInterface = woSendForApprovalInterface;
    }

    public void WOInvoiceRejectMethod(){
        woSendForApprovalInterface.SendForApproval();
        loginPageInterface.LoginMethod(properties.getProperty("FinanceChecker"));
        page.locator(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnReject").click();
        page.locator(".bootbox-input").fill("Rejected");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}