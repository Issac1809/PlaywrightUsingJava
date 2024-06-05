package com.yokogawa.requestforquotations.technicalevaluation;
import com.microsoft.playwright.Page;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;
import java.util.Properties;

public class TechnicalEvaluation implements TechnicalEvaluationInterface{

    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private TechnicalEvaluation(){
    }

//TODO Test Constructor
    public TechnicalEvaluation(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public TechnicalEvaluation(VariablesForNonCatalog variablesForNonCatalog, Page page, LoginPageInterface loginPageInterface, LogoutPageInterface logoutPageInterface){
        this.variablesForNonCatalog = variablesForNonCatalog;
        this.page = page;
        this.loginPageInterface = loginPageInterface;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void TechnicalEvaluationButton() {
        loginPageInterface.LoginMethod(properties.getProperty("EmailID"));
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
        page.locator("#btnApprove").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}