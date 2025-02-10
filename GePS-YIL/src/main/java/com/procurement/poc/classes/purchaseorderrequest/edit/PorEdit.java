package com.procurement.poc.classes.purchaseorderrequest.edit;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorEdit;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.purchaseorderrequests.LPorEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorEdit implements IPorEdit {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    private String url;

    private PorEdit(){
    }

//TODO Constructor
    public PorEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.url = properties.getProperty("appUrl");
    }

    public void porEdit() {
        try {
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);

        Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR.getLocator());
        waitForLocator(porNavigationBarLocator);
        porNavigationBarLocator.click();

        String title = properties.getProperty("currentTitle");
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

//        Response response = page.waitForResponse(
//                resp -> resp.url().startsWith(url + "/Procurement/PurchaseOrderRequests/POC_Details?uid") && resp.status() == 200,
//                acceptLocator::click
//        );

        statusAssertion(page, acceptLocator::click, "por", "Draft");

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}