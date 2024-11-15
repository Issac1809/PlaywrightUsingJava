package com.poc.classes.dispatchnotes.cancel;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.dispatchnotes.IDnCancel;
import com.poc.interfaces.dispatchnotes.IDnCreate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.dispatchnotes.LDnCancel.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class DnCancel implements IDnCancel {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IDnCreate iDnCreate;

    private DnCancel(){
    }

//TODO Constructor
    public DnCancel(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IDnCreate iDnCreate){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iDnCreate = iDnCreate;
    }

    public void cancel() {
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

            Locator dropDownLocator = page.locator(DROP_DOWN);
            waitForLocator(dropDownLocator);
            dropDownLocator.click();

            Locator cancelButtonLocator = page.locator(CANCEL_BUTTON);
            waitForLocator(cancelButtonLocator);
            cancelButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();

            iDnCreate.create();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}