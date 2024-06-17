package com.procurement.requestforquotations.technicalevaluation;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.TEReject;
import com.microsoft.playwright.Page;
import java.util.Properties;

public class TechnicalEvaluationReject implements TEReject {

    LoginPageInterface loginPageInterface;
    Properties properties;
    Page page;
    LogoutPageInterface logoutPageInterface;

    private TechnicalEvaluationReject(){
    }

//TODO Constructor
    public TechnicalEvaluationReject(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.page = page;
        this.properties = properties;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void TechnicalEvaluationRejectMethod(){
        loginPageInterface.LoginMethod();
        page.locator("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnCreateTE").click();
        page.locator(".border-primary").click();
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        page.locator("#btnSendApproval").click();
        page.locator(".select2-selection--single").first().click();
        String teApprover = properties.getProperty("TEApprover");
        page.locator(".select2-search__field").fill(teApprover);
        page.locator("//li[contains(text(), '"+ teApprover +"')]").click();
        page.locator("#saveApproverAssign").click();
        page.locator(".bootbox-accept").click();
        page.locator("#btnReject").click();
        page.locator(".bootbox-input").fill("TE Rejected");
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}