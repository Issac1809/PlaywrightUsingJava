package com.poc.classes.invoice.poinvoice.approve;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvApproval;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvApproval.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvApproval implements IInvApproval {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private InvApproval(){
    }

//TODO Constructor
    public InvApproval(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void approval(){
        try {
        String financeChecker = properties.getProperty("FinanceChecker");
        iLogin.performLogin(financeChecker);

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

        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
        waitForLocator(approveButtonLocator);
        approveButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}