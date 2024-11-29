package com.poc.classes.invoice.poinvoice.checklist;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvChecklistReject;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvChecklistReject.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvChecklistReject implements IInvChecklistReject {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private InvChecklistReject(){
    }

//TODO Constructor
    public InvChecklistReject(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void reject(){
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

            Locator checklistLocator = page.locator(CHECKLIST_BUTTON);
            waitForLocator(checklistLocator);
            checklistLocator.click();

            Locator rejectButtonLocator = page.locator(REJECT_CHECKLIST_BUTTON);
            waitForLocator(rejectButtonLocator);
            rejectButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
