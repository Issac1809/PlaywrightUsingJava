package com.procurement.poc.classes.purchaseorderrequest.create;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorCreate;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.purchaseorderrequests.LPorCreate.*;
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

    public void catalogPORCreate(){
        buyerPorCreate();
        porNotes();
        porCreate();
    }

    public void nonCatalogPORCreate(){
        try{
            String buyerMailId = properties.getProperty("buyerEmail");
            iLogin.performLogin(buyerMailId);

            Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR.getLocator());
            waitForLocator(rfqNavigationBarLocator);
            rfqNavigationBarLocator.click();

            String title = properties.getProperty("currentTitle");
            Locator titleLocator = page.locator(getString(title));
            waitForLocator(titleLocator);
            titleLocator.first().click();
//
//        Locator editButtonLocator = page.locator(EDIT_BUTTON.getAPI());
//        waitForLocator(editButtonLocator);
//        Response response = page.waitForResponse(
//                resp -> resp.url().startsWith(LOAD_PAGE.getAPI()) && resp.status() == 200,
//                editButtonLocator::click
//        );

            Locator porCreateButtonLocator = page.locator(POR_CREATE_BUTTON.getLocator());
            waitForLocator(porCreateButtonLocator);
            porCreateButtonLocator.first().click();

            Locator isPreselected = page.locator(PRESELECTED_YES.getLocator());
            waitForLocator(isPreselected);
            isPreselected.first().click();

            page.waitForLoadState(LoadState.NETWORKIDLE);
            Locator createButtonLocator = page.locator(CREATE_BUTTON.getLocator());
            waitForLocator(createButtonLocator);
            createButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES.getLocator());
            waitForLocator(yesButtonLocator);

            statusAssertion(page, yesButtonLocator::click, "por", "Draft");

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }



    public void buyerPorCreate() {
        try {
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);

//        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR.getLocator());
//        waitForLocator(rfqNavigationBarLocator);
//        rfqNavigationBarLocator.click();

        Locator requisitionNavBarLocator = page.locator(PR_NAVIGATION_BAR.getLocator());
        waitForLocator(requisitionNavBarLocator);
        requisitionNavBarLocator.click();

        String title = properties.getProperty("currentTitle");
        Locator titleLocator = page.locator(getString(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator porCreateButtonLocator = page.locator(CREATE.getLocator());
        waitForLocator(porCreateButtonLocator);
        porCreateButtonLocator.first().click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void justification(){
        try {
        Locator below5lLocator = page.locator(BELOW_5L.getLocator());
        waitForLocator(below5lLocator);
        below5lLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void taxCode(){
        try {
        Locator selectTaxCodesLocator = page.getByText(TAX_CODE.getLocator());
        waitForLocator(selectTaxCodesLocator);
        selectTaxCodesLocator.last().click();

        String taxCode = properties.getProperty("TaxCode");
        Locator taxCodeLocator = page.locator(getString(taxCode));
        waitForLocator(taxCodeLocator);
        taxCodeLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void porNotes() {
        try {
        String notes = properties.getProperty("porNotes");
        Locator porNotesLocator = page.locator(POR_NOTES.getLocator());
        waitForLocator(porNotesLocator);
        porNotesLocator.fill(notes);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void porCreate(){
        try {
            page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator createButtonLocator = page.locator(CREATE_BUTTON.getLocator());
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES.getLocator());
        waitForLocator(yesButtonLocator);

        statusAssertion(page, yesButtonLocator::click, "por", "Draft");

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}