package com.poc.classes.invoice.woinvoice.invreturn;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvReturn;
import com.poc.interfaces.invoices.woinvoices.IWoInvSendForApproval;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvReturn.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvReturn implements IWoInvReturn {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;
    IWoInvSendForApproval iWoInvSendForApproval;

    private WoInvReturn(){
    }

//TODO Constructor
    public WoInvReturn(ILogin iLogin, Properties properties, Page page, ILogout iLogout, IWoInvSendForApproval iWoInvSendForApproval){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.iWoInvSendForApproval = iWoInvSendForApproval;
    }

    public void returnMethod(){
        try {
            iWoInvSendForApproval.sendForApproval();

            String buyerMailId = properties.getProperty("Buyer");
            iLogin.performLogin(buyerMailId);

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

            Locator holdButtonLocator = page.locator(SUSPEND_BUTTON);
            waitForLocator(holdButtonLocator);
            holdButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            waitForLocator(remarksInputLocator);
            remarksInputLocator.fill("Returned");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
