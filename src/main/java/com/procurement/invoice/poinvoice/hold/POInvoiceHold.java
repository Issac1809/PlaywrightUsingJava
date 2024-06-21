package com.procurement.invoice.poinvoice.hold;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PoInvHold;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class POInvoiceHold implements PoInvHold {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private POInvoiceHold(){
    }

    //TODO Constructor
    public POInvoiceHold(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void POInvoiceHoldMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector(".nav-link   active").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.waitForSelector("#btnToHoldInvocie").click();
        page.waitForSelector(".bootbox-input").fill("Hold");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}