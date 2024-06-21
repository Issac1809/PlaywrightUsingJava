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
        try {
        woSendForApprovalInterface.SendForApproval();
        loginPageInterface.LoginMethod(properties.getProperty("FinanceChecker"));
        page.waitForSelector(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.waitForSelector("#btnReject").click();
        page.waitForSelector(".bootbox-input").fill("Rejected");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}