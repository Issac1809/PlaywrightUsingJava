package com.poc.classes.freightforwarderrequests.quote;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.freightforwarderrequests.IFfrQuote;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.freightforwarderrequests.LFfrQuote.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class FfrQuote implements IFfrQuote {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private FfrQuote(){
    }

//TODO Constructor
    public FfrQuote(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void quote() {
        try {
        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

        Locator ffrNavigationBarLocator = page.locator(FFR_NAVIGATION_BAR);
        waitForLocator(ffrNavigationBarLocator);
        ffrNavigationBarLocator.click();

        String dnReferenceId = properties.getProperty("DispatchNoteReferenceId");
        List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
        for(String tr : containerList){
            if(tr.contains(dnReferenceId)){
                Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                waitForLocator(detailsButtonLocator);
                detailsButtonLocator.first().click();
            }
        }

        Locator sendQuoteButtonLocator = page.locator(SEND_QUOTE_BUTTON);
        waitForLocator(sendQuoteButtonLocator);
        sendQuoteButtonLocator.click();

        String totalChargeableWeight = properties.getProperty("Total Chargeable Weight");
        Locator totalChargeableWeightLocator = page.locator(TOTAL_CHARGEABLE_WEIGHT);
        waitForLocator(totalChargeableWeightLocator);
        totalChargeableWeightLocator.fill(totalChargeableWeight);

        String unitRate = properties.getProperty("Unit Rate");
        Locator unitRateLocator = page.locator(UNIT_RATE);
        waitForLocator(unitRateLocator);
        unitRateLocator.fill(unitRate);

        Locator submitQuotationLocator = page.locator(SUBMIT_BUTTON);
        waitForLocator(submitQuotationLocator);
        submitQuotationLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}