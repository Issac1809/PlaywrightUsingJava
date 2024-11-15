package com.poc.classes.orderschedule.create;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.orderschedule.IOsCreate;
import java.util.Properties;
import static com.constants.orderschedule.LOsCreate.*;
import static com.constants.purchaseorders.LPoCreate.getTitle;
import static com.factory.PlaywrightFactory.waitForLocator;

public class OsCreate implements IOsCreate {

    PlaywrightFactory playwrightFactory;
    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsCreate() {
    }

//TODO Constructor
    public OsCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
    }

    public void create() {
        try {
        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

        Locator porNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
        waitForLocator(porNavigationBarLocator);
        porNavigationBarLocator.click();

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator poReferenceId = page.locator(PO_REFERENCE_ID);
        waitForLocator(poReferenceId);
        String getPoRefId = poReferenceId.textContent();

        playwrightFactory.saveToPropertiesFile("PoReferenceId", getPoRefId);

        Locator createOsButtonLocator = page.locator(CREATE_OS_BUTTON);
        waitForLocator(createOsButtonLocator);
        createOsButtonLocator.click();

        Locator orderScheduleDate = page.locator(SCHEDULE_DATE);
        waitForLocator(orderScheduleDate);
        orderScheduleDate.last().click();

        Locator todayLocator = page.locator(TODAY);
        waitForLocator(todayLocator);
        todayLocator.first().click();

        Locator createButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}