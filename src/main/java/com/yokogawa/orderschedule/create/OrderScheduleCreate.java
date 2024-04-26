package com.yokogawa.orderschedule.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;

import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;

public class OrderScheduleCreate implements OrderScheduleInterface {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public String OSCreate(String mailId, Page page){
        login.Login(mailId, page);
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        page.locator("//*[contains(text(), '"+ NonCatalogTitle +"')]").first().click();
        String poReferenceId = page.locator("#referenceId2").textContent();
        page.locator("#btnCreateOR").click();
        Locator orderScheduleDate = page.locator(".scheduleDate-1").last();
        orderScheduleDate.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#btnCreate").click();
        page.waitForSelector(".bootbox-accept").click();
        logout.Logout(page);
        System.out.println(poReferenceId);
        return poReferenceId;
    }
}
