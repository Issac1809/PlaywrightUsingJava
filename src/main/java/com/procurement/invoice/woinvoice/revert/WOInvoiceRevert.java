package com.procurement.invoice.woinvoice.revert;

import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PoInvRevert;
import com.interfaces.WoInvRevert;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOInvoiceRevert implements WoInvRevert {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WOInvoiceRevert(){
    }

//TODO Constructor
    public WOInvoiceRevert(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void WOInvoiceRevertMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.waitForSelector("#btnToRevertInvocie").click();
        page.waitForSelector(".bootbox-input").fill("Revert");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}