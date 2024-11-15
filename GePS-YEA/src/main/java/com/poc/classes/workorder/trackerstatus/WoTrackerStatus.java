package com.poc.classes.workorder.trackerstatus;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.workorders.IWoTrackerStatus;
import java.util.List;
import java.util.Properties;
import static com.constants.workorders.LWoTrackerStatus.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoTrackerStatus implements IWoTrackerStatus {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    PlaywrightFactory playwrightFactory;

    private WoTrackerStatus(){
    }

//TODO Constructor
    public WoTrackerStatus(ILogin iLogin, Properties properties, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory){
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
    }

    public void trackerStatus() {
        try {
        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

        Locator workOrderNavigationBarLocator = page.locator(WO_NAVIGATION_BAR);
        waitForLocator(workOrderNavigationBarLocator);
        workOrderNavigationBarLocator.click();

        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
        for(String tr : containerList){
            if(tr.contains(poReferenceId)){
                Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                waitForLocator(detailsButtonLocator);
                detailsButtonLocator.first().click();
            }
        }

        Locator woReferenceId = page.locator(WO_REFERENCE_ID);
        waitForLocator(woReferenceId);
        String getWoRefId = woReferenceId.innerText();

        playwrightFactory.saveToPropertiesFile("WOReferenceId", getWoRefId);

        for (String status : STATUSES) {
            Locator datePickerLocator = page.locator(DATE_PICKER);
            waitForLocator(datePickerLocator);
            datePickerLocator.click();

            Locator todayLocator = page.locator(TODAY);
            waitForLocator(todayLocator);
            todayLocator.first().click();

            Locator statusLocator = page.locator(STATUS_CONTAINER);
            waitForLocator(statusLocator);
            statusLocator.last().click();

            Locator statusSelectLocator = page.locator(getStatus(status));
            waitForLocator(statusSelectLocator);
            statusSelectLocator.click();

            Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
            waitForLocator(submitButtonLocator);
            submitButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();
        }

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}