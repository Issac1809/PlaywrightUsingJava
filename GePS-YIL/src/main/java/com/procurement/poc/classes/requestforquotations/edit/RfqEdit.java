package com.procurement.poc.classes.requestforquotations.edit;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IRfqEdit;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.requestforquotations.LRfqEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class RfqEdit implements IRfqEdit {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    Page page;
    private String url;

    private RfqEdit(){
    }

//TODO Constructor
    public RfqEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.url = properties.getProperty("appUrl");
            }

    public void rfqEditMethod() {
        try {
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR.getLocator());
        waitForLocator(rfqNavigationBarLocator);
        rfqNavigationBarLocator.click();

        String title = properties.getProperty("currentTitle");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();
        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator editButtonLocator = page.locator(EDIT_BUTTON.getAPI());
        waitForLocator(editButtonLocator);
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith(url + "/api/RequestForQuotations/") && resp.status() == 200,
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

        statusAssertion(page,acceptLocator::click,"rfq","Live");

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}