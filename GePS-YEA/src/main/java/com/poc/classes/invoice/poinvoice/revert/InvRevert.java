package com.poc.classes.invoice.poinvoice.revert;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvRevert;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvRevert.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvRevert implements IInvRevert {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private InvRevert(){
    }

//TODO Constructor
    public InvRevert(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void revert(){
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

            Locator revertButtonLocator = page.locator(REVERT_BUTTON);
            waitForLocator(revertButtonLocator);
            revertButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            waitForLocator(remarksInputLocator);
            remarksInputLocator.fill("Reverted");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}