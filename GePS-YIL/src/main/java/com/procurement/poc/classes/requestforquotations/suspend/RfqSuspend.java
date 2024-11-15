package com.procurement.poc.classes.requestforquotations.suspend;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IRfqCreate;
import com.procurement.poc.interfaces.requestforquotation.IRfqEdit;
import com.procurement.poc.interfaces.requestforquotation.IRfqSuspend;
import com.procurement.poc.interfaces.requisitions.IPrApprove;
import com.procurement.poc.interfaces.requisitions.IPrAssign;
import com.procurement.poc.interfaces.requisitions.IPrEdit;
import com.procurement.poc.interfaces.requisitions.IPrSendForApproval;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LRfqSuspend.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class RfqSuspend implements IRfqSuspend {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    Page page;
    IRfqEdit iRfqEdit;
    IPrEdit iPrEdit;
    IPrSendForApproval iPrSendForApproval;
    IPrApprove iPrApprove;
    IPrAssign iPrAssign;
    IRfqCreate iRfqCreate;

    private RfqSuspend(){
    }

//TODO Constructor
    public RfqSuspend(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IRfqEdit iRfqEdit, IPrEdit iPrEdit,
                      IPrSendForApproval iPrSendForApproval, IPrApprove iPrApprove, IPrAssign iPrAssign, IRfqCreate iRfqCreate){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iRfqEdit = iRfqEdit;
        this.iPrEdit = iPrEdit;
        this.iPrSendForApproval = iPrSendForApproval;
        this.iPrApprove = iPrApprove;
        this.iPrAssign = iPrAssign;
        this.iRfqCreate = iRfqCreate;
    }

    public void suspendRfqEdit() {
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

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        waitForLocator(suspendButtonLocator);
        suspendButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Suspended");

        Locator acceptLocator = page.locator(YES);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();

        iRfqEdit.rfqEditMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void suspendPREdit() {
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

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        waitForLocator(suspendButtonLocator);
        suspendButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Suspended");

        Locator acceptLocator = page.locator(YES);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        iPrEdit.edit();
        iPrSendForApproval.sendForApproval();
        iPrApprove.approve();
        iPrAssign.buyerManagerLogin();
        iPrAssign.buyerManagerAssign();
        iRfqCreate.buyerLogin();
        iRfqCreate.buyerRfqCreate();
        iRfqCreate.rfqNotes();
        iRfqCreate.rfqCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}