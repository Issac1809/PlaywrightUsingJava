package com.procurement.invoice.woinvoice.cancel;

import com.interfaces.inv.woinv.WOInvoiceCreateInterface;
import com.interfaces.inv.woinv.WoInvCancel;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WoInvoiceCancel implements WoInvCancel {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    WOInvoiceCreateInterface woInvoiceCreateInterface;

    private WoInvoiceCancel(){
    }

//TODO Constructor
    public WoInvoiceCancel(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, WOInvoiceCreateInterface woInvoiceCreateInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.woInvoiceCreateInterface = woInvoiceCreateInterface;
    }

    public void WoInvoiceCancelMethod(){
        try {
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
        woInvoiceCreateInterface.VendorCreateWOInvoice();
        double finalGSTPercentage = woInvoiceCreateInterface.VendorGST();
        woInvoiceCreateInterface.SGDEquivalentEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}