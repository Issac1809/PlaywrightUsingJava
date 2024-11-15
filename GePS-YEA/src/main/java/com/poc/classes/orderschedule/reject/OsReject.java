package com.poc.classes.orderschedule.reject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.orderschedule.IOsReject;
import java.util.Properties;
import static com.constants.orderschedule.LOsReject.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class OsReject implements IOsReject {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsReject(){
    }

//TODO Constructor
    public OsReject(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void reject(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
        waitForLocator(poNavigationBarLocator);
        poNavigationBarLocator.click();

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator viewOrderScheduleButtonLocator = page.locator(VIEW_ORDER_SCHEDULE__BUTTON);
        waitForLocator(viewOrderScheduleButtonLocator);
        viewOrderScheduleButtonLocator.click();

        Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
        waitForLocator(rejectButtonLocator);
        rejectButtonLocator.click();

        Locator remarksInoutLocator = page.locator(REMARKS_INPUT);
        waitForLocator(rejectButtonLocator);
        rejectButtonLocator.fill("Rejected");

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}