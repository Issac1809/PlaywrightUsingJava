package com.procurement.poc.classes.requestforquotations.edit;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IRfqEdit;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LRfqEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrCreate.POC_DETAILS_PAGE_API;

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
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR.getLocator());
        waitForLocator(rfqNavigationBarLocator);
        rfqNavigationBarLocator.click();

        String title = properties.getProperty("orderTitle");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator editButtonLocator = page.locator(EDIT_BUTTON.getAPI());
        waitForLocator(editButtonLocator);
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith(LOAD_PAGE.getAPI()) && resp.status() == 200,
                editButtonLocator::click
        );

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON.getLocator());
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_POP_UP.getLocator());
        waitForLocator(remarksLocator);
        remarksLocator.fill(REMARKS.getLocator());

        page.waitForLoadState(LoadState.NETWORKIDLE);
        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP.getLocator());
        waitForLocator(acceptLocator);

        Response response1 = page.waitForResponse(
                resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/api/requestforquotations") && resp.status() == 200,
                acceptLocator::click
        );

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}