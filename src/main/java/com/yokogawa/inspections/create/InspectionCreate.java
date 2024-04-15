package com.yokogawa.inspections.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.List;
public class InspectionCreate implements InspectionCreateInterface {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void VendorInspectionCreate(String mailId, String poReferenceId, Page page){
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Order Schedules')]").click();
        List<String> containerList = page.locator("#listContainer").allTextContents();
        for(String tr : containerList){
            if(tr.equals(poReferenceId)){
                Locator details = page.locator("//*[contains(text(), ' Details ')]");
                details.first().click();
            }
        }
        page.locator("#btnSendForInspection").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);
    }
}
