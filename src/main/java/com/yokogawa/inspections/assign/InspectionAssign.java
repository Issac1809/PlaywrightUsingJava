package com.yokogawa.inspections.assign;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;
import java.util.List;
import java.util.Properties;

public class InspectionAssign implements InspectionAssignInterface {

    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private InspectionAssign(){
    }

//TODO Test Constructor
    public InspectionAssign(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public InspectionAssign(VariablesForNonCatalog variablesForNonCatalog, Page page, LoginPageInterface loginPageInterface, LogoutPageInterface logoutPageInterface){
        this.variablesForNonCatalog = variablesForNonCatalog;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.loginPageInterface = loginPageInterface;
    }

    public void RequesterInspectionAssign(){
        String mailId = properties.getProperty("EmailID");
        loginPageInterface.LoginMethod(mailId);
        page.pause();
        page.locator("//*[contains(text(), 'Order Schedules')]").click();
        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        page.locator("#btnAssignInspector").click();
        page.locator("#select2-InspectionId-container").click();
        page.locator(".select2-search__field").fill(mailId);
        page.locator("//li[contains(text(), '"+ mailId +"')]").first().click();
        page.locator("#saveInspector").click();
        page.locator("#btnForCreateInspection").click();
        page.locator("#physicalInspectionNotReq").click();
        page.locator("#btnCreateInspection").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        }
}
