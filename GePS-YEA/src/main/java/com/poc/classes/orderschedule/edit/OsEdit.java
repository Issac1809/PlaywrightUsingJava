package com.poc.classes.orderschedule.edit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.orderschedule.IOsEdit;
import java.util.List;
import java.util.Properties;
import static com.constants.orderschedule.LOsEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class OsEdit implements IOsEdit {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsEdit(){
    }

//TODO Constructor
    public OsEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void edit(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

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

        Locator editButtonLocator = page.locator(EDIT_BUTTON);
        waitForLocator(editButtonLocator);
        editButtonLocator.click();

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        waitForLocator(updateButtonLocator);
        updateButtonLocator.click();

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}