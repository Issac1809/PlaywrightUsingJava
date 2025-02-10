package com.procurement.poc.classes.requisition.reject;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrEdit;
import com.procurement.poc.interfaces.requisitions.IPrReject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrReject.*;

public class Reject implements IPrReject {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private IPrEdit iPrEdit;
    private String url;

    private Reject(){
    }

//TODO Constructor
    public Reject(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPrEdit iPrEdit){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrEdit = iPrEdit;
        this.url = properties.getProperty("appUrl");
    }

    public void reject(String approver)  {
        try {
        iLogin.performLogin(approver);
        String title = properties.getProperty("currentTitle");
        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator rejectButtonLocator = page.locator(REJECT_BUTTON.getLocator());
        waitForLocator(rejectButtonLocator);
        rejectButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS.getLocator());
        waitForLocator(remarksLocator);
        remarksLocator.fill("Rejected");

        Locator yesButtonLocator = page.locator(YES.getLocator());
        waitForLocator(yesButtonLocator);

        statusAssertion(page, yesButtonLocator::click, "requisition", "Rejected");

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}