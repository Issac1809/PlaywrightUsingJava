package com.procurement.orderschedule.create;
import com.interfaces.os.OrderScheduleInterface;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.factory.PlayWrightFactory;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import java.util.Properties;

public class OrderScheduleCreate implements OrderScheduleInterface {

    PlayWrightFactory playWrightFactory;
    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private OrderScheduleCreate() {
    }

    public OrderScheduleCreate(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface, PlayWrightFactory playWrightFactory) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
        this.playWrightFactory = playWrightFactory;
    }

    public void OSCreate() {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        String poReferenceId = page.locator("#referenceId").textContent();
        playWrightFactory.savePropertiesToFile(poReferenceId);
        page.locator("#btnCreateOR").click();
        Locator orderScheduleDate = page.locator(".scheduleDate-1").last();
        orderScheduleDate.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}