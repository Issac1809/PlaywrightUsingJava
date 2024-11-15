package com.procurement.poc.classes.purchaseorderrequest.suspend;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorCreate;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorEdit;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorSuspend;
import com.procurement.poc.interfaces.requestforquotation.ICeCreate;

import java.util.Properties;

import static com.procurement.poc.constants.purchaseorderrequests.LPorSuspend.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorSuspend implements IPorSuspend {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IPorEdit iPorEdit;
    ICeCreate iCeCreate;
    IPorCreate iPorCreate;

    private PorSuspend(){
    }

//TODO Constructor
    public PorSuspend(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IPorEdit iPorEdit, ICeCreate iCeCreate, IPorCreate iPorCreate){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iPorEdit = iPorEdit;
        this.iCeCreate = iCeCreate;
        this.iPorCreate = iPorCreate;
    }

    public void suspend(){
        try {
            String buyerMailId = properties.getProperty("buyerEmail");
            iLogin.performLogin(buyerMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR.getLocator());
            waitForLocator(porNavigationBarLocator);
            porNavigationBarLocator.click();

            String title = properties.getProperty("orderTitle");
            Locator titleLocator = page.locator(getTitle(title));
            waitForLocator(titleLocator);
            titleLocator.first().click();

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON.getLocator());
            waitForLocator(suspendButtonLocator);
            suspendButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS_INPUT_LOCATOR.getLocator());
            waitForLocator(remarksLocator);
            remarksLocator.fill("Suspended");

            Locator acceptLocator = page.locator(YES.getLocator());
            waitForLocator(acceptLocator);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            System.out.println("What is the error: " + exception.getMessage());
        }
    }

    public void suspendPorEdit(){
        try {
            suspend();
//            iPorEdit.porEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void suspendRfqEdit(){
        try {
            suspend();
            iCeCreate.commercialEvaluationButton();
            iPorCreate.buyerPorCreate();
            iPorCreate.justification();
            iPorCreate.taxCode();
            iPorCreate.porNotes();
            iPorCreate.porCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
