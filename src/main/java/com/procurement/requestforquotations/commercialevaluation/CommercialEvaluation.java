package com.procurement.requestforquotations.commercialevaluation;
import com.interfaces.CommercialEvaluationInterface;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.util.Properties;

public class CommercialEvaluation implements CommercialEvaluationInterface {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private CommercialEvaluation(){
    }

//TODO Constructor
    public CommercialEvaluation(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void CommercialEvaluationButton(){
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnCreateCE").click();
        Locator selectionStatus = page.locator("select[onchange='isSelect(event)']");
        selectionStatus.click();
        selectionStatus.waitFor();
        selectionStatus.selectOption("Yes");
        page.locator("#btnSubmitCE").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}