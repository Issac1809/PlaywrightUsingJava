package com.procurement.invoice.poinvoice.edit;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.po.POSendForApprovalInterface;
import com.interfaces.inv.poinv.PoInvEdit;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class POInvoiceEdit implements PoInvEdit {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;
    POSendForApprovalInterface poSendForApprovalInterface;

    private POInvoiceEdit(){
    }

    //TODO Constructor
    public POInvoiceEdit(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, POSendForApprovalInterface poSendForApprovalInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.poSendForApprovalInterface = poSendForApprovalInterface;
    }

    public void POInvoiceEditMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//*[contains(text(),  'Invoices')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator("#listContainer tr td").allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("#btnEdit").click();
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}