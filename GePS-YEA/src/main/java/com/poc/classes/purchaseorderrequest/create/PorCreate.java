package com.poc.classes.purchaseorderrequest.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.purchaseorderrequests.IPorCreate;
import java.util.Properties;
import static com.constants.purchaseorderrequests.LPorCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorCreate implements IPorCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorCreate(){
    }

//TODO Constructor
    public PorCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void buyerPorCreate() {
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

        Locator porCreateButtonLocator = page.locator(POR_CREATE_BUTTON);
        waitForLocator(porCreateButtonLocator);
        porCreateButtonLocator.first().click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void justification(){
        try {
        Locator below5lLocator = page.locator(BELOW_5L);
        waitForLocator(below5lLocator);
        below5lLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void taxCode(){
        try {
        Locator selectTaxCodesLocator = page.getByText(TAX_CODE);
        waitForLocator(selectTaxCodesLocator);
        selectTaxCodesLocator.last().click();

        String taxCode = properties.getProperty("TaxCode");
        Locator taxCodeLocator = page.locator(getTaxCode(taxCode));
        waitForLocator(taxCodeLocator);
        taxCodeLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void porNotes() {
        try {
        String notes = properties.getProperty("PorNotes");
        Locator porNotesLocator = page.locator(POR_NOTES);
        waitForLocator(porNotesLocator);
        porNotesLocator.fill(notes);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void porCreate(){
        try {
        Locator createButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES);
        waitForLocator(yesButtonLocator);
        yesButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}