package com.procurement.poc.classes.requisition.suspend;
import com.microsoft.playwright.Response;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrEdit;
import com.procurement.poc.interfaces.requisitions.IPrSuspend;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;

import java.util.Properties;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrBuyerSuspend.*;

public class Suspend implements IPrSuspend {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private IPrEdit iPrEdit;

    private Suspend(){
    }

//TODO Constructor
    public Suspend(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPrEdit iPrEdit){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrEdit = iPrEdit;
    }

    public void suspend(){
        try {
        iLogin.performLogin(properties.getProperty("buyerManagerEmail"));
        boolean buyer = false;

        String title = properties.getProperty("orderTitle");
        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        String status = page.locator(STATUS.getLocator()).textContent();
        if(status.contains("Assigned")){
            iLogout.performLogout();
            iLogin.performLogin(properties.getProperty("buyerEmail"));
            waitForLocator(titleLocator);
            titleLocator.first().click();
            buyer = true;
        }
        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON.getLocator());
        waitForLocator(suspendButtonLocator);
        suspendButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS.getLocator());
        waitForLocator(remarksLocator);

        if(buyer == true)
            remarksLocator.fill("Buyer Suspended");
        else
            remarksLocator.fill("Buyer Manager Suspended");

        Locator yesButtonLocator = page.locator(YES.getLocator());
        waitForLocator(yesButtonLocator);
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/Procurement/Requisitions/POC_Details") && resp.status() == 200,
                yesButtonLocator::click
        );
        iLogout.performLogout();
//        iPrEdit.buyerSuspendEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}