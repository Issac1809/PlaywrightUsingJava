package com.procurement.poc.classes.purchaseorder.create;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.purchaseorders.IPoCreate;

import java.util.Properties;

import static com.procurement.poc.constants.purchaseorders.LPoCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PoCreate implements IPoCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PoCreate() {
    }

    //TODO Constructor
    public PoCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void createPO() {
        try {
            String adminMailId = properties.getProperty("AdminId");
            iLogin.performLogin(adminMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            waitForLocator(porNavigationBarLocator);
            porNavigationBarLocator.click();

            String title = properties.getProperty("Title");
            Locator titleLocator = page.locator(getTitle(title));
            waitForLocator(titleLocator);
            titleLocator.first().click();

            Locator createPOButtonLocator = page.locator(CREATE_PO_BUTTON);
            waitForLocator(createPOButtonLocator);
            createPOButtonLocator.evaluate("(element) => { element.style.display = 'block'; }");
            createPOButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptLocator);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            System.out.println("What is the Error: " + exception.getMessage());
        }
    }
}