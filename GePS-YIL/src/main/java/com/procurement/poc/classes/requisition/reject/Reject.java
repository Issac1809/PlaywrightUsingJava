package com.procurement.poc.classes.requisition.reject;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrEdit;
import com.procurement.poc.interfaces.requisitions.IPrReject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;

import java.util.Properties;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.nonPoc.constants.requisitions.LPrReject.*;

public class Reject implements IPrReject {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private IPrEdit iPrEdit;

    private Reject(){
    }

//TODO Constructor
    public Reject(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPrEdit iPrEdit){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrEdit = iPrEdit;
    }

    public void reject()  {
        try {
        iLogin.performLogin(properties.getProperty("projectManagerEmail"));
        String title = properties.getProperty("orderTitle");
        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
        waitForLocator(rejectButtonLocator);
        rejectButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS);
        waitForLocator(remarksLocator);
        remarksLocator.fill("Rejected");

        Locator yesButtonLocator = page.locator(YES);
        waitForLocator(yesButtonLocator);
        yesButtonLocator.click();

        iLogout.performLogout();
        iPrEdit.rejectEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}