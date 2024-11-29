package com.poc.classes.invoice.woinvoice.checklist;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvChecklistReject;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvChecklistReject.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvChecklistReject implements IWoInvChecklistReject {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvChecklistReject(){
    }

//TODO Constructor
    public WoInvChecklistReject(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void reject(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator invoiceNaviagtionBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
        waitForLocator(invoiceNaviagtionBarLocator);
        invoiceNaviagtionBarLocator.click();

        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceTable = page.locator(LIST_CONTAINER).allTextContents();
        for (String tr : invoiceTable){
            if (tr.contains(woReferenceId)) {
                Locator listContainerLocator = page.locator(INVOICE_SELECT);
                waitForLocator(listContainerLocator);
                listContainerLocator.first().click();
            }
        }

        Locator checkListLocator = page.locator(CHECKLIST_BUTTON);
        waitForLocator(checkListLocator);
        checkListLocator.click();

        Locator addToReviewQuequeLocator = page.locator(REJECT_CHECKLIST_BUTTON);
        waitForLocator(addToReviewQuequeLocator);
        addToReviewQuequeLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
