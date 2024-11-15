package com.poc.classes.dispatchnotes.assign;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.dispatchnotes.IDnAssign;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.disptchnotes.LDnAssign.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class DnAssign implements IDnAssign {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    PlaywrightFactory playwrightFactory;

    private DnAssign(){
    }

//TODO Constructor
    public DnAssign(ILogin iLogin, Properties properties, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory){
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
    }

    public void assign() {
        try {
            String logisticsManagerMailId = properties.getProperty("LogisticsManager");
            iLogin.performLogin(logisticsManagerMailId);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            waitForLocator(dnNavigationBarLocator);
            dnNavigationBarLocator.click();

            String poReferenceId = properties.getProperty("PoReferenceId");
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    waitForLocator(detailsButtonLocator);
                    detailsButtonLocator.first().click();
                }
            }

            Locator dnReferenceId = page.locator(DISPATCH_NOTES_REFERENCE_ID);
            waitForLocator(dnReferenceId);
            String getDnRefId = dnReferenceId.innerText();

            playwrightFactory.saveToPropertiesFile("DnReferenceId", getDnRefId);

            Locator dropDownLocator = page.locator(DROP_DOWN);
            waitForLocator(dropDownLocator);
            dropDownLocator.click();

            Locator assignButtonLocator = page.locator(ASSIGN_BUTTON);
            waitForLocator(assignButtonLocator);
            assignButtonLocator.click();

            Locator assignDropDownLocator = page.locator(SELECT_LOGISTICS_MANAGER_DROP_DOWN);
            waitForLocator(assignDropDownLocator);
            assignDropDownLocator.click();

            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
            waitForLocator(searchFieldLocator);
            searchFieldLocator.fill(logisticsManagerMailId);

            Locator getLogisticsManagerMailId = page.locator(getLogisticsManagerId(logisticsManagerMailId));
            waitForLocator(getLogisticsManagerMailId);
            getLogisticsManagerMailId.click();

            Locator saveButtonLocator = page.locator(SAVE_BUTTON);
            waitForLocator(saveButtonLocator);
            saveButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}