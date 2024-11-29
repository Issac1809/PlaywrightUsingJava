package com.poc.classes.invoice.poinvoice.edit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.poinvoices.IInvEdit;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.poinvoice.LInvEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class InvEdit implements IInvEdit {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private InvEdit(){
    }

//TODO Constructor
    public InvEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void edit(){
        try {
            String vendorMailId = properties.getProperty("VendorMailId");
            iLogin.performLogin(vendorMailId);

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

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            waitForLocator(editButtonLocator);
            editButtonLocator.click();

            Locator popUpLocator = page.locator(POP_UP_ACCEPT);
            waitForLocator(popUpLocator);
            popUpLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptLocator);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}