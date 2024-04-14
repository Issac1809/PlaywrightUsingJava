package com.yokogawa.requestforquotations.technicalevaluation;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
public class TechnicalEvaluation implements TechnicalEvaluationInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void TechnicalEvaluationButton(String mailId, String TEApprover, Page page){
        login.Login(mailId, page);
        page.locator("#btnCreateTE").click();
        page.locator(".border-primary").click();
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        page.locator("#btnSendApproval").click();
        page.locator(".select2-selection--single").first().click();
        page.locator(".select2-search__field").fill(TEApprover);
        page.locator("//li[contains(text(), '"+ TEApprover +"')]").click();
        page.locator("#saveApproverAssign").click();
        page.locator(".bootbox-accept").click();
        page.locator("#btnApprove").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);
    }
}
