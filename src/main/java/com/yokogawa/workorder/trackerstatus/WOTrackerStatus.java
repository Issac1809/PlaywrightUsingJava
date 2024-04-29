package com.yokogawa.workorder.trackerstatus;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.List;
public class WOTrackerStatus implements WOTrackerStatusInterface{
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void VendorTrackerStatus(String mailId, String poReferenceId, Page page) {
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Work Orders')]").click();
        List<String> containerList = page.locator("#listContainer tr td").allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                page.locator(".btn-link").first().click();
            }
        }
        String[] status = {"Material_Pick_Up", "ETD", "Arrival_Notification", "Import_Clearance", "Out_for_Delivery", "Delivery_Completed"};
        for(int i = 0; i < status.length; i++) {
            page.locator(".form-control.form-control-sm.flatpickr-custom.form-control.input").click();
            Locator today = page.locator("//span[@class='flatpickr-day today']").first();
            today.click();
            page.locator("#select2-statusId-container").last().click();
            page.locator("//li[contains(text(), '"+ status[i] +"')]").click();
            page.locator("#btnSubmit").click();
            page.locator(".bootbox-accept").click();
        }
        logout.Logout(page);
    }
}
