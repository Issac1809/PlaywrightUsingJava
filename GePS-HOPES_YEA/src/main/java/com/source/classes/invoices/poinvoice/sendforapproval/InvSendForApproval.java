package com.source.classes.invoices.poinvoice.sendforapproval;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.poinvoices.IInvSendForApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.poinvoice.LInvSendForApproval.*;

public class InvSendForApproval implements IInvSendForApproval {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private InvSendForApproval(){
    }

//TODO Constructor
    public InvSendForApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(InvSendForApproval.class);
    }

    public void sendForApproval(){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

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

            Locator sendForApprovalLocator = page.locator(SEND_FOR_APPROVAL_BUTTON);
            sendForApprovalLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Send For Approval", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Send For Approval function: {}", exception.getMessage());
        }
    }
}