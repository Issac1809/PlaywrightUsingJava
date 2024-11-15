package com.poc.classes.inspections.readyforinspection;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.inspections.IInsReadyForInspection;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.inspections.LInsCreate.ACCEPT_BUTTON;
import static com.constants.inspections.LInsReadyForInspection.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InsReadyForInspection implements IInsReadyForInspection {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private InsReadyForInspection(){
    }

//TODO Constructor
    public InsReadyForInspection(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void readyForInspection(){
        try {
        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

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

        Locator readyForInspectionButtonLocator = page.locator(READY_FOR_INSPECTION_BUTTON);
        waitForLocator(readyForInspectionButtonLocator);
        readyForInspectionButtonLocator.click();

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}