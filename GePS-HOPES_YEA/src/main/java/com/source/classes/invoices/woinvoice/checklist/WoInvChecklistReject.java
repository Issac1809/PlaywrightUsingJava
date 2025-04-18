package com.source.classes.invoices.woinvoice.checklist;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.woinvoices.IWoInvChecklistReject;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvChecklistReject.*;


public class WoInvChecklistReject implements IWoInvChecklistReject {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvChecklistReject(){
    }

//TODO Constructor
    public WoInvChecklistReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoInvChecklistReject.class);
    }

    public void reject(){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator invoiceNaviagtionBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNaviagtionBarLocator.click();

            String woReferenceId = jsonNode.get("workOrders").get("workOrderReferenceId").asText();
            List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : invoiceContainer){
                if (tr.contains(woReferenceId)){
                    Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
                    invoiceSelectLocator.first().click();
                }
                break;
            }

            Locator checkListLocator = page.locator(CHECKLIST_BUTTON);
            checkListLocator.click();

            Locator addToReviewQuequeLocator = page.locator(REJECT_CHECKLIST_BUTTON);
            addToReviewQuequeLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Reject function: {}", exception.getMessage());
        }
    }
}