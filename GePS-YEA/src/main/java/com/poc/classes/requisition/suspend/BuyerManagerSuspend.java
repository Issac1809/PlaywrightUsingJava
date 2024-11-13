package com.poc.classes.requisition.suspend;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.requisitions.IPrBuyerManagerSuspend;
import com.poc.interfaces.requisitions.IPrEdit;
import java.util.Properties;
import static com.constants.requisitions.LPrBuyerManagerSuspend.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class BuyerManagerSuspend implements IPrBuyerManagerSuspend {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    Page page;
    IPrEdit iPrEdit;

    private BuyerManagerSuspend(){
    }

//TODO Constructor
    public BuyerManagerSuspend(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPrEdit iPrEdit){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrEdit = iPrEdit;
    }

    public void suspend() {
        try {
            String buyerManagerMailId = properties.getProperty("BuyerManager");
            iLogin.performLogin(buyerManagerMailId);

            String title = properties.getProperty("Title");
            Locator titleLocator = page.locator(getTitle(title));
            waitForLocator(titleLocator);
            titleLocator.first().click();

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            waitForLocator(suspendButtonLocator);
            suspendButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS);
            waitForLocator(remarksLocator);
            remarksLocator.fill("Buyer Manager Suspended");

            Locator acceptLocator = page.locator(YES);
            waitForLocator(acceptLocator);
            acceptLocator.click();

            iLogout.performLogout();
            iPrEdit.buyerManagerSuspendEdit();

        } catch (Exception exception) {
            System.out.println("What is the Error: " + exception.getMessage());
        }
    }
}
