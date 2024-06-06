package com.yokogawa.orderschedule.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.playwrightfactory.PlayWrightFactory;
import com.yokogawa.login.LoginPageInterface;
import com.yokogawa.logout.LogoutPageInterface;
import com.yokogawa.variables.VariablesForNonCatalog;
import java.util.Properties;

public class OrderScheduleCreate implements OrderScheduleInterface {

    PlayWrightFactory playWrightFactory;
    Properties properties;
    VariablesForNonCatalog variablesForNonCatalog;
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
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        page.pause();
        page.locator("//*[contains(text(), 'Purchase Orders')]").click();
        String title = properties.getProperty("Title");
        page.locator("//*[contains(text(), '" + title + "')]").first().click();
        String poReferenceId = page.locator("#referenceId").textContent();
        System.out.println(poReferenceId);
        playWrightFactory.savePropertiesToFile(poReferenceId);
        page.locator("#btnCreateOR").click();
        Locator orderScheduleDate = page.locator(".scheduleDate-1").last();
        orderScheduleDate.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']").first();
        today.click();
        page.locator("#btnCreate").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
    }
}