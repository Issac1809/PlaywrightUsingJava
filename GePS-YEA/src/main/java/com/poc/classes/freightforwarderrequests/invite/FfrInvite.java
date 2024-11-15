package com.poc.classes.freightforwarderrequests.invite;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.freightforwarderrequests.IFfrInvite;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.freightforwarderrequests.LFfrInvite.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class FfrInvite implements IFfrInvite {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private FfrInvite(){
    }

//TODO Constructor
    public FfrInvite(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void invite() {
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

        Locator inviteFreightForwarderButtonLocator = page.locator(INVITE_VENDOR_BUTTON);
        waitForLocator(inviteFreightForwarderButtonLocator);
        inviteFreightForwarderButtonLocator.click();

        Locator dropDownLocator = page.locator(DROP_DOWN);
        waitForLocator(dropDownLocator);
        dropDownLocator.click();

        String freightVendor = properties.getProperty("Vendor");
        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
        waitForLocator(searchFieldLocator);
        searchFieldLocator.fill(freightVendor);

        Locator freightForwarderLocator = page.locator(getFreightForwarder(freightVendor));
        waitForLocator(freightForwarderLocator);
        freightForwarderLocator.click();

        Locator saveButtonLocator = page.locator(SAVE_BUTTON);
        waitForLocator(saveButtonLocator);
        saveButtonLocator.click();

        Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
        waitForLocator(emailPopUpLocator);
        emailPopUpLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}