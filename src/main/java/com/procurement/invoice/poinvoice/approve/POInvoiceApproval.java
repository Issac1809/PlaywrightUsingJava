package com.procurement.invoice.poinvoice.approve;
import com.interfaces.POInvoiceApprovalInterface;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class POInvoiceApproval implements POInvoiceApprovalInterface {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private POInvoiceApproval(){
    }

//TODO Constructor
    public POInvoiceApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void POInvoiceApprovalMethod(){
        loginPageInterface.LoginMethod(properties.getProperty("FinanceChecker"));
        page.locator(".nav-link   active").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnApprove").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}