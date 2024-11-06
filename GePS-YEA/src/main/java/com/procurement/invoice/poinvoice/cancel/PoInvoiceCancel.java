package com.procurement.invoice.poinvoice.cancel;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.inv.poinv.POInvoiceCreateInterface;
import com.interfaces.inv.poinv.PoInvCancel;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class PoInvoiceCancel implements PoInvCancel {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    POInvoiceCreateInterface poInvoiceCreateInterface;

    private PoInvoiceCancel(){
    }

//TODO Constructor
    public PoInvoiceCancel(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, POInvoiceCreateInterface poInvoiceCreateInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.poInvoiceCreateInterface = poInvoiceCreateInterface;
    }

    public void PoInvoiceCancelMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnToSuspendInvoice").click();
        page.locator(".bootbox-input").fill("Cancelled");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        poInvoiceCreateInterface.VendorCreatePOInvoice();
        double finalGSTPercentage = poInvoiceCreateInterface.VendorGST();
        poInvoiceCreateInterface.SGDEquivalentEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}