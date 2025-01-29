package com.ps.classes.requisition.assign;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrAssign;
import java.util.Properties;
import static com.constants.requisitions.LPrAssign.*;

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
        String title = properties.getProperty("orderTitle");
        String buyerMailId = properties.getProperty("buyerEmail");
        String getTitle = getTitle(title);

        Locator titleLocator = page.locator(getTitle);
        titleLocator.first().click();

        Locator assignUser = page.locator(ASSIGN_USER);
        assignUser.click();

        Locator selectAssignUser = page.locator(SELECT_ASSIGN_USER);
        selectAssignUser.click();

        page.locator(SEARCHBOX).fill(buyerMailId);
        String getBuyerMailId = getBuyerMailId(buyerMailId);
        Locator buyerManager = page.locator(getBuyerMailId);
        buyerManager.first().click();
        Locator saveUser = page.locator(SAVE_USER);
        saveUser.click();
        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}