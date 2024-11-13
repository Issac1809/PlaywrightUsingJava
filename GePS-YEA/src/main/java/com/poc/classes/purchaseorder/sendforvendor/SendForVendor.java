package com.poc.classes.purchaseorder.sendforvendor;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.purchaseorders.IPoSendForVendor;
import java.util.Properties;
import static com.constants.purchaseorderrequests.LPorCreate.getTitle;
import static com.constants.purchaseorders.LPoSendForVendor.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class SendForVendor implements IPoSendForVendor {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private SendForVendor(){
    }

//TODO Constructor
    public SendForVendor(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void sendForVendor(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
        waitForLocator(poNavigationBarLocator);
        poNavigationBarLocator.click();

        String title = properties.getProperty("Title");
        Locator titleLocator = page.locator(getTitle(title));
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator sendForVendorButtonLocator = page.locator(SEND_FOR_VENDOR_BUTTON);
        waitForLocator(sendForVendorButtonLocator);
        sendForVendorButtonLocator.click();

        Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
        waitForLocator(emailPopUpLocator);
        emailPopUpLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}