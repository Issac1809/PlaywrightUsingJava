package com.source.classes.invoices.poinvoice.checklist;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.poinvoices.IInvChecklistReject;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.LIST_CONTAINER;
import static com.constants.invoices.poinvoice.LInvApproval.INVOICE_SELECT;
import static com.constants.invoices.poinvoice.LInvChecklistReject.*;

public class InvChecklistReject implements IInvChecklistReject {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private InvChecklistReject(){
    }

//TODO Constructor
    public InvChecklistReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(InvChecklistReject.class);
    }

    public void reject(){
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

            Locator checklistLocator = page.locator(CHECKLIST_BUTTON);
            checklistLocator.click();

            Locator rejectButtonLocator = page.locator(REJECT_CHECKLIST_BUTTON);
            rejectButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Checklist Reject", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Checklist Reject function: {}", exception.getMessage());
        }
    }
}