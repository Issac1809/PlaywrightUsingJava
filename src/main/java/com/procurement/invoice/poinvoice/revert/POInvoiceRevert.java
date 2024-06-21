package com.procurement.invoice.poinvoice.revert;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PoInvRevert;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class POInvoiceRevert implements PoInvRevert {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private POInvoiceRevert(){
    }

    //TODO Constructor
    public POInvoiceRevert(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void POInvoiceRevertMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnToRevertInvocie").click();
        page.locator(".bootbox-input").fill("Revert");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}