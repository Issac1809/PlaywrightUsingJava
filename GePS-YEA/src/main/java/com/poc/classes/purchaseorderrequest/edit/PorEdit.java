package com.poc.classes.purchaseorderrequest.edit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.purchaseorderrequests.IPorEdit;
import java.util.Properties;
import static com.constants.purchaseorderrequests.LPorEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorEdit implements IPorEdit {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorEdit(){
    }

//TODO Constructor
    public PorEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void porEdit() {
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
        waitForLocator(porNavigationBarLocator);
        porNavigationBarLocator.click();

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator editButtonLocator = page.locator(EDIT_BUTTON);
        waitForLocator(editButtonLocator);
        editButtonLocator.click();

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Updated");

        Locator acceptLocator = page.locator(YES);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}