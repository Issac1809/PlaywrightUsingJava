package com.poc.classes.dispatchnotes.dnreturn;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.dispatchnotes.IDnEdit;
import com.poc.interfaces.dispatchnotes.IDnReturn;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.disptchnotes.LDnReturn.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class DnReturn implements IDnReturn {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IDnEdit iDnEdit;

    private DnReturn(){
    }

//TODO Constructor
    public DnReturn(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IDnEdit iDnEdit){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iDnEdit = iDnEdit;
    }

    public void dnReturn() {
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

        Locator dropDownLocator = page.locator(DROP_DOWN);
        waitForLocator(dropDownLocator);
        dropDownLocator.click();

        Locator returnButtonLocator = page.locator(RETURN_BUTTON);
        waitForLocator(returnButtonLocator);
        returnButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_FIELD);
        waitForLocator(remarksLocator);
        remarksLocator.fill("Returned");

        Locator acceptLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}