package com.procurement.invoice.woinvoice.approve;

import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.POInvoiceApprovalInterface;
import com.interfaces.WOInvoiceApprovalInterface;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceApproval implements WOInvoiceApprovalInterface {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WOInvoiceApproval(){
    }

//TODO Constructor
    public WOInvoiceApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void WOInvoiceApprovalMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("FinanceChecker"));
        page.locator(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnApprove").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}