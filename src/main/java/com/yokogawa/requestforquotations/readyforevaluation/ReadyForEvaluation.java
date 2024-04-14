package com.yokogawa.requestforquotations.readyforevaluation;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
public class ReadyForEvaluation implements ReadyForEvalutationInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void ReadyForEvaluationButton(String mailId, Page page){
        login.Login(mailId, page);
        page.locator("#btnReadyForEvalution").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);
    }
}
