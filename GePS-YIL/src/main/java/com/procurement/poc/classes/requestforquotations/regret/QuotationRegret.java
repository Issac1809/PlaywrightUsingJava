package com.procurement.poc.classes.requestforquotations.regret;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IQuoRegret;
import com.procurement.poc.interfaces.requestforquotation.IQuoSubmit;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LQuoRegret.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class QuotationRegret implements IQuoRegret {

    ILogin iLogin;
    ILogout iLogout;
    IQuoSubmit iQuoSubmit;
    Properties properties;
    Page page;

    private QuotationRegret(){
    }

//TODO Constructor
    public QuotationRegret(IQuoSubmit iQuoSubmit, ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iQuoSubmit = iQuoSubmit;
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void regret(){
        try {
        iQuoSubmit.inviteRegisteredVendor();

        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator regretButtonLocator = page.locator(REGRET_BUTTON);
        waitForLocator(regretButtonLocator);
        regretButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_POP_UP);
        waitForLocator(remarksLocator);
        remarksLocator.fill(REMARKS);

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}