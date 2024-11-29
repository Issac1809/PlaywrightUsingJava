package com.poc.classes.invoice.woinvoice.edit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvEdit;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvEdit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvEdit implements IWoInvEdit {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvEdit(){
    }

//TODO Constructor
    public WoInvEdit(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
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

            String woReferenceId = properties.getProperty("WorkOrderReferenceId");
            List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : invoiceContainer){
                if (tr.contains(woReferenceId)){
                    Locator listContainerLocator = page.locator(INVOICE_SELECT);
                    waitForLocator(listContainerLocator);
                    listContainerLocator.first().click();
                }
            }

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            waitForLocator(editButtonLocator);
            editButtonLocator.click();

            Locator createButtonLocator = page.locator(POP_UP_ACCEPT);
            waitForLocator(createButtonLocator);
            createButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}