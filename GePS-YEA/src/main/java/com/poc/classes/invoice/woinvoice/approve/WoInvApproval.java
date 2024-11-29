package com.poc.classes.invoice.woinvoice.approve;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvApproval;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvApproval.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvApproval implements IWoInvApproval {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvApproval(){
    }

//TODO Constructor
    public WoInvApproval(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
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

        String woReferenceId = properties.getProperty("WorkOrderReferenceId");
        List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
        for(String tr : invoiceContainer){
            if (tr.contains(woReferenceId)){
                Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
                waitForLocator(invoiceSelectLocator);
                invoiceSelectLocator.first().click();
            }
            break;
        }

        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
        waitForLocator(approveButtonLocator);
        approveButtonLocator.click();

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}