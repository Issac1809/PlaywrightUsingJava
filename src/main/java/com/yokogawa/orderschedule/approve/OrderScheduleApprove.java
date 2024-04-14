package com.yokogawa.orderschedule.approve;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;

public class OrderScheduleApprove implements OrderScheduleApproveInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void OSApprove(String mailId, Page page){
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        page.locator("//*[contains(text(), '"+ NonCatalogTitle +"'").first().click();
        page.locator("#BtnToApproveOS").click();
        page.locator("#btnApprove").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);

    }
}
