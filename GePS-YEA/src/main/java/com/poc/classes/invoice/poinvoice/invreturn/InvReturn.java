package com.poc.classes.invoice.poinvoice.invreturn;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvReturn;
import com.poc.interfaces.invoices.poinvoices.IInvSendForApproval;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvReturn.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvReturn implements IInvReturn {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;
    IInvSendForApproval iInvSendForApproval;

    private InvReturn(){
    }

//TODO Constructor
    public InvReturn(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IInvSendForApproval iInvSendForApproval){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iInvSendForApproval = iInvSendForApproval;
    }

    public void invReturn(){
        try {
        iInvSendForApproval.sendForApproval();

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

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}