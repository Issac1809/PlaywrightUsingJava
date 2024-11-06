package com.procurement.invoice.woinvoice.checklist;

import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.inv.woinv.WoInvAccept;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOChecklistAccept implements WoInvAccept {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WOChecklistAccept(){
    }

    //TODO Constructor
    public WOChecklistAccept(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void ChecklistAcceptMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceTable = page.locator("#listContainer tr td").allTextContents();
        for (String tr : invoiceTable){
            if (tr.contains(woReferenceId)) {
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.locator("//*[contains(text(), 'Check List')]").click();
        page.locator("#selctAllId").first().click();
        page.locator("#acceptCheckListId").click();
        page.locator("#btnAccept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}