package com.procurement.poc.classes.requestforquotations.create;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IRfqCreate;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LRfqCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class RfqCreate implements IRfqCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private RfqCreate(){
    }

//TODO Constructor
    public RfqCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void createRFQ(){
        buyerLogin();
        buyerRfqCreate();
        rfqNotes();
        rfqCreate();
    }

    public void buyerLogin() {
        try {
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }


    public void buyerRfqCreate() {
        try {
        String title = properties.getProperty("orderTitle");
        Locator getTitleLocator = page.locator(getTitle(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        Locator createButton = page.locator(CREATE_RFQ_BUTTON.getLocator());
            Response response = page.waitForResponse(
                    resp -> resp.url().startsWith(LOAD_PROJECT.getAPI()) && resp.status() == 200,
                    createButton::click
            );

        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rfqNotes() {
        try {
        String rfqNotes = properties.getProperty("rfqNotes");
        Locator notesLocator = page.locator(NOTES.getLocator());
        waitForLocator(notesLocator);
        notesLocator.fill(rfqNotes);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rfqCreate() {
        try {
        Locator createButtonLocator = page.locator(CREATE_BUTTON.getLocator());
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES_BUTTON.getLocator());
        waitForLocator(yesButtonLocator);
        yesButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}