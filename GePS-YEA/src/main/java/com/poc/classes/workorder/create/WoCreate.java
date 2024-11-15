package com.poc.classes.workorder.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.workorders.IWoCreate;
import java.util.List;
import java.util.Properties;
import static com.constants.workorders.LWoCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoCreate implements IWoCreate {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private WoCreate(){
    }

//TODO Constructor
    public WoCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void create() {
        try {
        String logisticsManager = properties.getProperty("LogisticsManager");
        iLogin.performLogin(logisticsManager);

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

        Locator dropDownLocator = page.locator(ACTION_DROPDOWN);
        waitForLocator(dropDownLocator);
        dropDownLocator.click();

        Locator createWorkOrderButtonLocator = page.locator(CREATE_WORK_ORDER_BUTTON);
        waitForLocator(createWorkOrderButtonLocator);
        createWorkOrderButtonLocator.click();

        Locator selectFreightForwarderLocator = page.locator(FREIGHT_FORWARDER_DROPDOWN);
        waitForLocator(selectFreightForwarderLocator);
        selectFreightForwarderLocator.first().click();

        String vendorId = properties.getProperty("Vendor");
        Locator searchField = page.locator(SEARCH_FIELD);
        waitForLocator(searchField);
        searchField.fill(vendorId);

        Locator getVendorLocator = page.locator(getVendor(vendorId));
        waitForLocator(getVendorLocator);
        getVendorLocator.first().click();

        Locator priorityLocator = page.locator(PRIORITY_DROPDOWN);
        waitForLocator(priorityLocator);
        priorityLocator.click();

        String priority = properties.getProperty("Priority");
        Locator highOptionLocator = page.locator(getPriority(priority));
        waitForLocator(highOptionLocator);
        highOptionLocator.click();

        Locator dateLocator = page.locator(DATE_LOCATOR);
        waitForLocator(dateLocator);
        dateLocator.last().click();

        Locator todayLocator = page.locator(TODAY);
        waitForLocator(todayLocator);
        todayLocator.first().click();

        Locator destinationTermLocator = page.locator(DESTINATION_FIELD);
        waitForLocator(destinationTermLocator);
        destinationTermLocator.fill("India");

        Locator proceedButtonLocator = page.locator(PROCEED_BUTTON);
        waitForLocator(proceedButtonLocator);
        proceedButtonLocator.click();

        Locator emailPopUpLocator = page.locator(SEND_MAIL_BUTTON);
        waitForLocator(emailPopUpLocator);
        emailPopUpLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}