package com.procurement.invoice.woinvoice.verify;

import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PoInvVerify;
import com.interfaces.WoInvVerify;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceVerify implements WoInvVerify {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WOInvoiceVerify(){
    }

    //TODO Constructor
    public WOInvoiceVerify(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void WOInvoiceVerifyMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceTable = page.locator("#listContainer tr td").allTextContents();
        for (String tr : invoiceTable){
            if (tr.contains(woReferenceId)) {
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnAccept").click();
        page.locator(".bootbox-input").fill("Verified");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}