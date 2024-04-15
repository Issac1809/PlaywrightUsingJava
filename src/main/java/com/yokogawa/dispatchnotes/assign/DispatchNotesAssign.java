package com.yokogawa.dispatchnotes.assign;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.List;
public class DispatchNotesAssign implements DispatchNotesAssignInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void DNAssign(String mailId, String poReferenceId, String logisticsManager, Page page) {
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Dispatch Notes')]").click();
        List<String> containerList = page.locator("#listContainer").allTextContents();
        for (String tr : containerList) {
            if (tr.equals(poReferenceId)) {
                Locator details = page.locator("//*[contains(text(), ' Details ')]");
                details.first().click();
            }
        }
        page.locator("#dnActionDropdown").click();
        page.locator("#btnToAssign").click();
        page.locator("#select2-assignerId-container").click();
        page.locator(".select2-search__field").fill(logisticsManager);
        page.locator("//li[contains(text(), '" + logisticsManager + "')]").click();
        page.locator("#saveAssine").click();
    }
}

