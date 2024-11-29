package com.poc.classes.invoice.poinvoice.cancel;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvCancel;
import com.poc.interfaces.invoices.poinvoices.IInvCreate;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvCancel.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvCancel implements IInvCancel {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;
    IInvCreate iInvCreate;

    private InvCancel(){
    }

//TODO Constructor
    public InvCancel(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IInvCreate iInvCreate){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iInvCreate = iInvCreate;
    }

    public void cancel(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
        waitForLocator(invoiceNavigationBarLocator);
        invoiceNavigationBarLocator.click();

        String poReferenceId = properties.getProperty("PoReferenceId");
        List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(poReferenceId)){
                Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
                waitForLocator(invoiceSelectLocator);
                invoiceSelectLocator.first().click();
            }
        }

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        waitForLocator(suspendButtonLocator);
        suspendButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Cancelled");

        Locator acceptLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();

        iInvCreate.create();
        double finalGSTPercentage = iInvCreate.gst();
        iInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}