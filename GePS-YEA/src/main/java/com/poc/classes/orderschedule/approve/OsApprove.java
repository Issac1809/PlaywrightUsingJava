package com.poc.classes.orderschedule.approve;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.orderschedule.IOsApprove;
import java.util.List;
import java.util.Properties;
import static com.constants.orderschedule.LOsApprove.*;
import static com.factory.PlaywrightFactory.*;

public class OsApprove implements IOsApprove {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsApprove(){
    }

//TODO Constructor
    public OsApprove(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void approve(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
        waitForLocator(poNavigationBarLocator);
        poNavigationBarLocator.click();

        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                waitForLocator(detailsButtonLocator);
                detailsButtonLocator.first().click();
            }
        }

        Locator viewOrderScheduleButtonLocator = page.locator(VIEW_ORDER_SCHEDULE__BUTTON);
        waitForLocator(viewOrderScheduleButtonLocator);
        viewOrderScheduleButtonLocator.click();

        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
        waitForLocator(approveButtonLocator);
        approveButtonLocator.click();

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}