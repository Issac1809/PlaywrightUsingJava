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
        try {
        loginPageInterface.LoginMethod();
        page.waitForSelector("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.waitForSelector("#btnCreateTE").click();
        page.waitForSelector(".border-primary").click();
        page.waitForSelector("#btnCreate").click();
        page.waitForSelector(".bootbox-accept").click();
        page.waitForSelector("#btnSendApproval").click();
        page.locator(".select2-selection--single").first().click();
        String teApprover = properties.getProperty("TEApprover");
        page.waitForSelector(".select2-search__field").fill(teApprover);
        page.waitForSelector("//li[contains(text(), '"+ teApprover +"')]").click();
        page.waitForSelector("#saveApproverAssign").click();
        page.waitForSelector(".bootbox-accept").click();
        page.waitForSelector("#btnReject").click();
        page.waitForSelector(".bootbox-input").fill("TE Rejected");
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}