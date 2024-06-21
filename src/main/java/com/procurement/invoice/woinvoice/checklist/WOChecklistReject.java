package com.procurement.invoice.woinvoice.checklist;

import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.WoInvChecklistReject;
import com.microsoft.playwright.Page;

import java.util.List;
import java.util.Properties;

public class WOChecklistReject implements WoInvChecklistReject {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private WOChecklistReject(){
    }

    //TODO Constructor
    public WOChecklistReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void ChecklistRejectMethod(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector(".nav-link   active").click();
        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceTable = page.locator("#listContainer tr td").allTextContents();
        for (String tr : invoiceTable){
            if (tr.contains(woReferenceId)) {
                page.locator(".btn btn-sm btn-link p-0 text-primary").first().click();
            }
        }
        page.waitForSelector("//*[contains(text(), 'Check List')]").click();
        page.waitForSelector("#addToReviewId").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
