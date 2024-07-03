package com.procurement.invoice.poinvoice.reject;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.POSendForApprovalInterface;
import com.interfaces.PoInvReject;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class POInvoiceReject implements PoInvReject {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    POSendForApprovalInterface poSendForApprovalInterface;

    private POInvoiceReject(){
    }

//TODO Constructor
    public POInvoiceReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, POSendForApprovalInterface poSendForApprovalInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.poSendForApprovalInterface = poSendForApprovalInterface;
    }

    public void POInvoiceRejectMethod(){
        try {
        poSendForApprovalInterface.SendForApproval();
        loginPageInterface.LoginMethod(properties.getProperty("FinanceChecker"));
        page.locator(".nav-link   active").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnReject").click();
        page.locator(".bootbox-input").fill("Rejected");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}