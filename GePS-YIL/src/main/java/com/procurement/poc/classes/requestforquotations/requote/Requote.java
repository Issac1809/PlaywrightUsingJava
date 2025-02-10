package com.procurement.poc.classes.requestforquotations.requote;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IQuoRequote;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LQuoRequote.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class Requote implements IQuoRequote {

    ILogin iLogin;
    Properties properties;
    Page page;
    ILogout iLogout;

    private Requote(){
    }

//TODO Constructor
    public Requote(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void requote() {
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarButtonLocator = page.locator(RFQ_NAVIGATION_BAR_BUTTON);
        waitForLocator(rfqNavigationBarButtonLocator);
        rfqNavigationBarButtonLocator.click();

        String title = properties.getProperty("currentTitle");
        Locator getTitleLocator = page.locator(getTitle(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        Locator requoteButtonLocator = page.locator(REQUOTE_BUTTON);
        waitForLocator(requoteButtonLocator);
        requoteButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
        waitForLocator(emailPopUpLocator);
        emailPopUpLocator.click();

        iLogout.performLogout();

        String vendorEmailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorEmailId);

        Locator getTitleLocator1 = page.locator(getTitle(title));
        waitForLocator(getTitleLocator1);
        getTitleLocator1.first().click();

        Locator requoteEditButton = page.locator(REQUOTE_EDIT_BUTTON);
        waitForLocator(requoteEditButton);
        requoteEditButton.click();

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator acceptLocator1 = page.locator(ACCEPT_REMARKS_POP_UP);
        waitForLocator(acceptLocator1);
        acceptLocator1.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
