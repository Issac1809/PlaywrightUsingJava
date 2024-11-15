package com.poc.classes.inspections.assign;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.inspections.IInsAssign;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.inspections.LInsAssign.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InsAssign implements IInsAssign {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private InsAssign() {
    }

//TODO Constructor
    public InsAssign(ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void assign() {
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

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}