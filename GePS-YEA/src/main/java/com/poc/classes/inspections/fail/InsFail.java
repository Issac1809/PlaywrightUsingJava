package com.poc.classes.inspections.fail;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.inspections.IInsFail;
import com.poc.interfaces.inspections.IInsReadyForInspection;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.inspections.LInsFail.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InsFail implements IInsFail {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IInsReadyForInspection iInsReadyForInspection;

    private InsFail() {
    }

//TODO Constructor
    public InsFail(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IInsReadyForInspection iInsReadyForInspection) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iInsReadyForInspection = iInsReadyForInspection;
    }

    public void fail() {
        try {
        String mailId = properties.getProperty("EmailID");
        iLogin.performLogin(mailId);

        Locator osNavigationBarLocator = page.locator(OS_NAVIGATION_BAR);
        waitForLocator(osNavigationBarLocator);
        osNavigationBarLocator.click();

        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                waitForLocator(detailsButtonLocator);
                detailsButtonLocator.first().click();
            }
        }

        Locator assignButtonLocator = page.locator(ASSIGN_INSPECTOR_BUTTON);
        waitForLocator(assignButtonLocator);
        assignButtonLocator.click();

        Locator dropDownLocator = page.locator(SELECT_INSPECTOR_DROP_DOWN);
        waitForLocator(dropDownLocator);
        dropDownLocator.click();

        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
        waitForLocator(searchFieldLocator);
        searchFieldLocator.fill(mailId);

        Locator requesterMailIdLocator = page.locator(getRequestorId(mailId));
        waitForLocator(requesterMailIdLocator);
        requesterMailIdLocator.first().click();

        Locator saveInspectorButtonLocator = page.locator(SAVE_INSPECTOR);
        waitForLocator(saveInspectorButtonLocator);
        saveInspectorButtonLocator.click();

        Locator createButtonLocator = page.locator(CREATE_INSPECTION_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator radioButton = page.locator(PHYSICAL_INSPECTION_NOT_REQUIRED);
        waitForLocator(radioButton);
        radioButton.click();

        Locator failButtonLocator = page.locator(INSPECTION_FAIL_BUTTON);
        waitForLocator(failButtonLocator);
        failButtonLocator.click();

        Locator createInspectionButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createInspectionButtonLocator);
        createInspectionButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksLocator);
        remarksLocator.fill("Failed");

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();

        iInsReadyForInspection.readyForInspection();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}