package com.procurement.requestforquotations.technicalevaluation;
import com.interfaces.TechnicalEvaluationInterface;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class TechnicalEvaluation implements TechnicalEvaluationInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private TechnicalEvaluation(){
    }

//TODO Constructor
    public TechnicalEvaluation(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void TechnicalEvaluationButton() {
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
        page.waitForSelector("#btnApprove").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}