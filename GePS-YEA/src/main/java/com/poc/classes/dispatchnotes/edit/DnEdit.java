package com.poc.classes.dispatchnotes.edit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.dispatchnotes.IDnEdit;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.dispatchnotes.LDnEdit.*;
import static com.constants.dispatchnotes.LDnReturn.ACCEPT_BUTTON;
import static com.factory.PlaywrightFactory.waitForLocator;

public class DnEdit implements IDnEdit {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private DnEdit(){
    }

//TODO Constructor
    public DnEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void edit() {
        try {
        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

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

        Locator editButtonLocator = page.locator(EDIT_BUTTON);
        waitForLocator(editButtonLocator);
        editButtonLocator.click();

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}