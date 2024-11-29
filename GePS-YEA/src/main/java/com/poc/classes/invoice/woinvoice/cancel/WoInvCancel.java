package com.poc.classes.invoice.woinvoice.cancel;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvCancel;
import com.poc.interfaces.invoices.woinvoices.IWoInvCreate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvCancel.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvCancel implements IWoInvCancel {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;
    IWoInvCreate iWoInvCreate;

    private WoInvCancel(){
    }

//TODO Constructor
    public WoInvCancel(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IWoInvCreate iWoInvCreate){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iWoInvCreate = iWoInvCreate;
    }

    public void cancel(){
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

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        waitForLocator(suspendButtonLocator);
        suspendButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Cancelled");

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();

        iWoInvCreate.create();
        double finalGSTPercentage = iWoInvCreate.gst();
        iWoInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}