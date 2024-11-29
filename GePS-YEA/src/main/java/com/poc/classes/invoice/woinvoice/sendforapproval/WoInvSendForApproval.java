package com.poc.classes.invoice.woinvoice.sendforapproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.invoices.woinvoices.IWoInvSendForApproval;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import java.util.List;
import java.util.Properties;
import static com.constants.invoices.woinvoice.LInvSendForApproval.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class WoInvSendForApproval implements IWoInvSendForApproval {

    Page page;
    Properties properties;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvSendForApproval(){
    }

//TODO Constructor
    public WoInvSendForApproval(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.page = page;
        this.properties = properties;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void sendForApproval(){
        try {
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

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON);
            waitForLocator(sendForApprovalButtonLocator);
            sendForApprovalButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            waitForLocator(acceptButtonLocator);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}