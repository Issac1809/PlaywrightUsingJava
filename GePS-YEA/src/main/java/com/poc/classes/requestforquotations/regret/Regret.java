package com.poc.classes.requestforquotations.regret;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.requestforquotation.IQuoRegret;
import java.util.Properties;
import static com.constants.requestforquotations.LQuoRegret.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class Regret implements IQuoRegret {

    ILogin iLogin;
    ILogout iLogout;
    QuotationSubmit quotationSubmit;
    Properties properties;
    Page page;

    private Regret(){
    }

//TODO Constructor
    public Regret(QuotationSubmit quotationSubmit, ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.quotationSubmit = quotationSubmit;
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void regret(){
        try {
        quotationSubmit.InviteRegisteredVendor();

        String vendorMailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorMailId);

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator regretButtonLocator = page.locator(REGRET_BUTTON);
        waitForLocator(regretButtonLocator);
        regretButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_POP_UP);
        waitForLocator(remarksLocator);
        remarksLocator.fill(REMARKS);

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}