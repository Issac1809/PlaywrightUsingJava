package com.procurement.poc.classes.requestforquotations.commercialevaluation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requestforquotation.ICeCreate;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.requestforquotations.LCeCreate.*;
import static com.procurement.poc.constants.requisitions.LPrAssign.getTitle;
import static com.factory.PlaywrightFactory.waitForLocator;

public class CommercialEvaluation implements ICeCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private CommercialEvaluation(){
    }

//TODO Constructor
    public CommercialEvaluation(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void commercialEvaluationButton(){
        try {
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);
        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
        waitForLocator(rfqNavigationBarLocator);
        rfqNavigationBarLocator.click();

        String title = properties.getProperty("currentTitle");
        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        statusAssertion(page,page::reload,"rfq","TEApproved");

        Locator createButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator selectionStatusLocator = page.locator(SELECTION_CRITERIA);
        waitForLocator(selectionStatusLocator);
        selectionStatusLocator.click();
        selectionStatusLocator.selectOption(OPTION);

        Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
        waitForLocator(submitButtonLocator);
        submitButtonLocator.click();

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
//        acceptButtonLocator.click();

        statusAssertion(page,acceptButtonLocator::click,"rfq","CESubmitted");

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}