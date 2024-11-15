package com.procurement.poc.classes.requestforquotations.edit;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IRfqEdit;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LRfqEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class RfqEdit implements IRfqEdit {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    Page page;

    private RfqEdit(){
    }

//TODO Constructor
    public RfqEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void rfqEditMethod() {
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
        waitForLocator(rfqNavigationBarLocator);
        rfqNavigationBarLocator.click();

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