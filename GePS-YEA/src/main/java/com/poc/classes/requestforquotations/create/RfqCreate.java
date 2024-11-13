package com.poc.classes.requestforquotations.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.requestforquotation.IRfqCreate;
import java.util.Properties;
import static com.constants.requestforquotations.LRfqCreate.*;
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

    public void buyerLogin() {
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void buyerRfqCreate() {
        try {
        String title = properties.getProperty("Title");
        Locator getTitleLocator = page.locator(getTitle(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        page.locator(CREATE_RFQ_BUTTON).click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rfqNotes() {
        try {
        String rfqNotes = properties.getProperty("RfQNotes");
        Locator notesLocator = page.locator(NOTES);
        waitForLocator(notesLocator);
        notesLocator.fill(rfqNotes);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rfqCreate() {
        try {
        Locator createButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES_BUTTON);
        waitForLocator(yesButtonLocator);
        yesButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}