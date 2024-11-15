package com.poc.classes.freightforwarderrequests.requote;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.freightforwarderrequests.IFfrQuote;
import com.poc.interfaces.freightforwarderrequests.IFfrRequote;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.freightforwarderrequests.LFfrReQuote.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class FfrRequote implements IFfrRequote {

    ILogin iLogin;
    ILogout iLogout;
    Properties properties;
    IFfrQuote iFfrQuote;
    Page page;

    private FfrRequote(){
    }

//TODO Constructor
    public FfrRequote(ILogin iLogin, Properties properties, IFfrQuote iFfrQuote, ILogout iLogout, Page page){
        this.iLogin = iLogin;
        this.properties = properties;
        this.iFfrQuote = iFfrQuote;
        this.iLogout = iLogout;
        this.page = page;
    }

    public void requote(){
        try {
        String logisticsManager = properties.getProperty("LogisticsManager");
        iLogin.performLogin(logisticsManager);

        Locator dnNavigationBarLocator = page.locator(FFR_NAVIGATION_BAR);
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


            iFfrQuote.quote();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}