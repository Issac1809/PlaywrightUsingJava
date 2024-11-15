package com.poc.classes.inspections.create;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.inspections.IInsCreate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.inspections.LInsCreate.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InsCreate implements IInsCreate {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    Page page;

    private InsCreate(){
    }

//TODO Constructor
    public InsCreate(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void create(){
        try{
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

            Locator createButtonLocator = page.locator(CREATE_INSPECTION_BUTTON);
            waitForLocator(createButtonLocator);
            createButtonLocator.click();

            Locator radioButton = page.locator(PHYSICAL_INSPECTION_NOT_REQUIRED);
            waitForLocator(radioButton);
            radioButton.click();

            Locator createInspectionButtonLocator = page.locator(CREATE_BUTTON);
            waitForLocator(createInspectionButtonLocator);
            createInspectionButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}