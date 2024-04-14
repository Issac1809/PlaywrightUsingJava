package com.yokogawa.requestforquotations.commercialevaluation;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
public class CommercialEvaluation implements CommercialEvaluationInterface {
    Login login = new LoginPage();
    public void CommercialEvaluationButton(String mailId, Page page){
        login.Login(mailId, page);
        page.locator("#btnCreateCE").click();
        Locator selectionStatus = page.locator("//*[@onchange = 'isSelect(event)']");
        selectionStatus.locator("//option[contains(text(), 'Yes')]").click();
        page.locator("#btnSubmitCE").click();
        page.locator(".bootbox-accept").click();
    }
}
