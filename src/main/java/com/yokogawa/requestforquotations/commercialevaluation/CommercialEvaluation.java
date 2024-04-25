package com.yokogawa.requestforquotations.commercialevaluation;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;
public class CommercialEvaluation implements CommercialEvaluationInterface {
    Login login = new LoginPage();
    public void CommercialEvaluationButton(String mailId, Page page){
        int i = 1;
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Request For Quotations')]").click();
        page.locator("//span[contains(text(), '"+ NonCatalogTitle +"')]").first().click();
        page.locator("#btnCreateCE").click();
        Locator selectionStatus = page.locator("select[onchange='isSelect(event)']");
        selectionStatus.click();
        selectionStatus.waitFor();
        selectionStatus.selectOption("Yes");
        page.locator("#btnSubmitCE").click();
        page.locator(".bootbox-accept").click();
    }
}