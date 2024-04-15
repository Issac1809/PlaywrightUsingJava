package com.yokogawa.workorder.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.List;
public class WorkOrderCreate implements WorkOrderCreateInterface {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void WOCreate(String mailId, String poReferenceId, String vendor, Page page) {
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
        page.locator("#btnToCreateWorkOrder").click();
        page.locator("#select2-freightForwarderId-container").first().click();
        page.locator(".select2-search__field").fill(vendor);
        page.locator("//li[contains(text(), '" + vendor + "')]").first().click();
        page.locator("#select2-priority-container").click();
        page.locator("//li[contains(text(), 'High')]").click();
        page.getByPlaceholder("Select Date").last().click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#destinationTermLocationId").fill("India");
        page.locator("//*[contains(text(), 'Proceed')]").click();
        page.locator("#vendorSendMailBtnId").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);
    }
}
