package com.source.classes.invoices.poinvoice.reject;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.poinvoices.IInvReject;
import com.source.interfaces.invoices.poinvoices.IInvSendForApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.poinvoice.LInvReject.*;


public class InvReject implements IInvReject {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    IInvSendForApproval iInvSendForApproval;

    private InvReject(){
    }

//TODO Constructor
    public InvReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IInvSendForApproval iInvSendForApproval){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iInvSendForApproval = iInvSendForApproval;
        this.logger = LoggerUtil.getLogger(InvReject.class);
    }

    public void reject(){
        try {
            iInvSendForApproval.sendForApproval();

            String financeCheckerMailId = jsonNode.get("mailIds").get("financeCheckerEmail").asText();
            iLogin.performLogin(financeCheckerMailId);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(INVOICE_SELECT);
                    detailsButtonLocator.first().click();
                }
            }

            Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
            rejectButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Rejected");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Reject", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Reject function: {}", exception.getMessage());
        }
    }
}