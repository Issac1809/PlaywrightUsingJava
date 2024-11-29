package com.poc.classes.invoice.woinvoice.hold;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvHold;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvHold.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvHold implements IWoInvHold {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvHold(){
    }

//TODO Constructor
    public WoInvHold(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void hold(){
        try {
            String buyerMailId = properties.getProperty("Buyer");
            iLogin.performLogin(buyerMailId);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            waitForLocator(invoiceNavigationBarLocator);
            invoiceNavigationBarLocator.click();

            String woReferenceId = properties.getProperty("WorkOrderReferenceId");
            List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : invoiceContainer){
                if (tr.contains(woReferenceId)){
                    Locator listContainerLocator = page.locator(INVOICE_SELECT);
                    waitForLocator(listContainerLocator);
                    listContainerLocator.first().click();
                }
            }

            Locator holdButtonLocator = page.locator(HOLD_BUTTON);
            waitForLocator(holdButtonLocator);
            holdButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            waitForLocator(remarksInputLocator);
            remarksInputLocator.fill("Hold");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}