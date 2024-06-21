package com.procurement.invoice.poinvoice.checklist;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PoInvAccept;
import com.microsoft.playwright.Page;
import java.util.List;
import java.util.Properties;

public class ChecklistAccept implements PoInvAccept {

    Page page;
    Properties properties;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private ChecklistAccept(){
    }

    //TODO Constructor
    public ChecklistAccept(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.page = page;
        this.properties = properties;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void ChecklistAcceptMethod(){
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
        logoutPageInterface.LogoutMethod();
    }
}