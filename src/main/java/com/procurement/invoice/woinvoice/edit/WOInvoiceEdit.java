package com.procurement.invoice.woinvoice.edit;

import com.interfaces.*;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceEdit implements WoInvEdit {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    WOSendForApprovalInterface woSendForApprovalInterface;

    private WOInvoiceEdit(){
    }

    //TODO Constructor
    public WOInvoiceEdit(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, WOSendForApprovalInterface woSendForApprovalInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.woSendForApprovalInterface = woSendForApprovalInterface;
    }

    public void WOInvoiceEditMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.waitForSelector("//*[contains(text(),  'Invoices')]").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.waitForSelector("#btnEdit").click();
        page.waitForSelector("#btnCreate").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}