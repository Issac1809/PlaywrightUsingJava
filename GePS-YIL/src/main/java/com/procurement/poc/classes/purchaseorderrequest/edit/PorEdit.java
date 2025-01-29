package com.procurement.poc.classes.purchaseorderrequest.edit;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorEdit;

import java.util.Properties;

import static com.procurement.poc.constants.purchaseorderrequests.LPorApprove.ACCEPT_BUTTON;
import static com.procurement.poc.constants.purchaseorderrequests.LPorEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorEdit implements IPorEdit {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorEdit(){
    }

//TODO Constructor
    public PorEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void porEdit() {
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

        Locator editButtonLocator = page.locator(EDIT_BUTTON.getLocator());
        waitForLocator(editButtonLocator);
        editButtonLocator.click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON.getLocator());
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT.getLocator());
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Updated");

        Locator acceptLocator = page.locator(YES.getLocator());
        waitForLocator(acceptLocator);

        Response response = page.waitForResponse(
                resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/Procurement/PurchaseOrderRequests/POC_Details?uid") && resp.status() == 200,
                acceptLocator::click
        );

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}