package com.source.classes.invoices.poinvoice.approve;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.poinvoices.IInvApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.LIST_CONTAINER;
import static com.constants.invoices.poinvoice.LInvApproval.*;

public class InvApproval implements IInvApproval {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private InvApproval(){
    }

//TODO Constructor
    public InvApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(InvApproval.class);
    }

    public void approval(){
        try {
            String financeChecker = jsonNode.get("mailIds").get("financeCheckerEmail").asText();
            iLogin.performLogin(financeChecker);

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

            Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
            approveButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Approve", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Approval function: {}", exception.getMessage());
        }
    }
}