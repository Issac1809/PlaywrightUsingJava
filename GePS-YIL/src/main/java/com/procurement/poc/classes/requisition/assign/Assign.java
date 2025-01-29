package com.procurement.poc.classes.requisition.assign;
import java.util.Properties;

import com.microsoft.playwright.Response;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrAssign;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrAssign.*;

public class Assign implements IPrAssign {

    private Page page;
    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;

//TODO Constructor    
    private Assign(){
    }

//TODO Constructor
    public Assign(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void buyerManagerLogin() {
        try {
            String buyerManager = properties.getProperty("buyerManagerEmail");
            iLogin.performLogin(buyerManager);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void buyerManagerAssign() {
        try {
            buyerManagerLogin();
        String title = properties.getProperty("orderTitle");
        String buyerMailId = properties.getProperty("buyerEmail");
        String getTitle = getTitle(title);

        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator assignUser = page.locator(ASSIGN_USER.getLocator());
        waitForLocator(assignUser);
        assignUser.click();

        Locator selectAssignUser = page.locator(SELECT_ASSIGN_USER.getLocator());
        waitForLocator(selectAssignUser);
        selectAssignUser.click();

        page.locator(SEARCHBOX.getLocator()).fill(buyerMailId);
        String getBuyerMailId = getBuyerMailId(buyerMailId);
        Locator buyerManager = page.locator(getBuyerMailId);
        waitForLocator(buyerManager);
        buyerManager.first().click();
        Locator saveUser = page.locator(SAVE_USER.getLocator());
        waitForLocator(saveUser);
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/Procurement/Requisitions/POC_Details") && resp.status() == 200,
                saveUser::click
        );
        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}