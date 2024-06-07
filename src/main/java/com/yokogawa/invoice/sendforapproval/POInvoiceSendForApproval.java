package com.yokogawa.invoice.sendforapproval;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import java.util.List;
import java.util.Properties;

public class POInvoiceSendForApproval implements POSendForApprovalInterface{

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private POInvoiceSendForApproval(){
    }

//TODO Constructor
    public POInvoiceSendForApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void SendForApproval(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceTable = page.locator("#listContainer tr td").allTextContents();
        for (String tr : invoiceTable){
            if (tr.contains(poReferenceId)) {
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("//*[contains(text(), 'Check List')]").click();
        page.locator("#selctAllId").first().click();
        page.locator("#acceptCheckListId").click();
        page.locator("#btnAccept").click();
        page.locator(".bootbox-accept").click();
        page.locator("#btnSendApproval").click();
        page.locator(".btn btn-primary bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}