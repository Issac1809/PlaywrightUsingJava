package com.procurement.poc.classes.requestforquotations.technicalevaluation;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requestforquotation.ITeCreate;

import java.util.Properties;

import static com.procurement.poc.constants.requestforquotations.LTeCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class TechnicalEvaluation implements ITeCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private TechnicalEvaluation(){
    }

//TODO Constructor
    public TechnicalEvaluation(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void technicalEvaluationButton() {
        try {
        iLogin.performLogin(properties.getProperty("requesterEmail"));

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR.getLocator());
        waitForLocator(rfqNavigationBarLocator);
        rfqNavigationBarLocator.click();

        String title = properties.getProperty("currentTitle");
        Locator titleLocator = page.locator(getString(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator teCreateButtonLocator = page.locator(TE_CREATE_BUTTON.getLocator());
        waitForLocator(teCreateButtonLocator);
        teCreateButtonLocator.click();

        Locator vendorSelectCheckboxLocator = page.locator(VENDOR_SELECT_CHECKBOX.getLocator());
        waitForLocator(vendorSelectCheckboxLocator);
        vendorSelectCheckboxLocator.click();

        Locator createTeButtonLocator = page.locator(CREATE_TECHNICAL_EVALUATION_BUTTON.getLocator());
        waitForLocator(createTeButtonLocator);
        createTeButtonLocator.click();

        Locator remarksAccept = page.locator(YES.getLocator());
        waitForLocator(remarksAccept);
        remarksAccept.click();

        Locator sendForApprovalLocator = page.locator(SEND_FOR_APPROVAL.getLocator());
        waitForLocator(sendForApprovalLocator);
        sendForApprovalLocator.click();

        Locator teApproverSelectLocator = page.locator(APPROVER_SELECT.getLocator());
//        waitForLocator(teApproverSelectLocator);
        teApproverSelectLocator.first().click();

        String teApprover = properties.getProperty("teApprover");
        Locator teApproverSearchLocator = page.locator(SEARCH_FIELD.getLocator());
        waitForLocator(teApproverSearchLocator);
        teApproverSearchLocator.fill(teApprover);

        Locator getTeApproverLocator = page.locator(getTeApprover(teApprover));
        waitForLocator(getTeApproverLocator);
        getTeApproverLocator.click();

        Locator saveApproverLocator = page.locator(SAVE_APPROVER.getLocator());
        waitForLocator(saveApproverLocator);
        saveApproverLocator.click();

        Locator acceptLocator = page.locator(YES.getLocator());
        waitForLocator(acceptLocator);
        acceptLocator.click();

        Locator approveButtonLocator = page.locator(APPROVE_BUTTON.getLocator());
        waitForLocator(approveButtonLocator);
        approveButtonLocator.click();

        Locator acceptLocator1 = page.locator(YES.getLocator());
        waitForLocator(acceptLocator1);
        acceptLocator1.click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}