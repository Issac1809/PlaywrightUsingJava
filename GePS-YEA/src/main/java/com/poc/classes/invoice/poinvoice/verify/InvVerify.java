package com.poc.classes.invoice.poinvoice.verify;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvVerify;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvVerify.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvVerify implements IInvVerify {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private InvVerify(){
    }

//TODO Constructor
    public InvVerify(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void verify(){
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

            Locator verifyButtonLocator = page.locator(VERIFY_BUTTON);
            waitForLocator(verifyButtonLocator);
            verifyButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            waitForLocator(remarksInputLocator);
            remarksInputLocator.fill("Verified");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}