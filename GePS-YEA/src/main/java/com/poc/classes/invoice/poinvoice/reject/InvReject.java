package com.poc.classes.invoice.poinvoice.reject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvReject;
import com.poc.interfaces.invoices.poinvoices.IInvSendForApproval;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvReject.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvReject implements IInvReject {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;
    IInvSendForApproval iInvSendForApproval;

    private InvReject(){
    }

//TODO Constructor
    public InvReject(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IInvSendForApproval iInvSendForApproval){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iInvSendForApproval = iInvSendForApproval;
    }

    public void reject(){
        try {
        iInvSendForApproval.sendForApproval();

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

        Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
        waitForLocator(rejectButtonLocator);
        rejectButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        waitForLocator(remarksInputLocator);
        remarksInputLocator.fill("Rejected");

        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
        waitForLocator(acceptButtonLocator);
        acceptButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}