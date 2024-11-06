package com.procurement.invoice.poinvoice.checklist;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.inv.poinv.PoInvChecklistReject;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class ChecklistReject implements PoInvChecklistReject {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private ChecklistReject(){
    }

    //TODO Constructor
    public ChecklistReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void ChecklistRejectMethod(){
        try {
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
        page.locator("#addToReviewId").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
