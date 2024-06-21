package com.procurement.invoice.poinvoice.invreturn;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.POSendForApprovalInterface;
import com.interfaces.PoInvReturn;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class POInvoiceReturn implements PoInvReturn {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    POSendForApprovalInterface poSendForApprovalInterface;

    private POInvoiceReturn(){
    }

    //TODO Constructor
    public POInvoiceReturn(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, POSendForApprovalInterface poSendForApprovalInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.poSendForApprovalInterface = poSendForApprovalInterface;
    }

    public void POInvoiceReturnMethod(){
        poSendForApprovalInterface.SendForApproval();

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
    }
}
