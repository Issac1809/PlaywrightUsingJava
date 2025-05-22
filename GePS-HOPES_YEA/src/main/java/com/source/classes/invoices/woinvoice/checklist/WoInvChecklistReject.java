package com.source.classes.invoices.woinvoice.checklist;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
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
    String appUrl;

    private WoInvChecklistReject(){
    }

//TODO Constructor
    public WoInvChecklistReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoInvChecklistReject.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int reject(){
        int status = 0;
        try {
            String buyerMailId = jsonNode.get("mailIds").get("financeCheckerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator invoiceNaviagtionBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNaviagtionBarLocator.click();

            String woReferenceId = jsonNode.get("invoices").get("workOrderInvoiceReferenceId").asText();
            Locator invoiceTitle = page.locator(getTitle(woReferenceId));
            invoiceTitle.click();

            Locator checkListLocator = page.locator(CHECKLIST_BUTTON);
            checkListLocator.first().click();
            Locator addToReviewQuequeLocator = page.locator(REJECT_CHECKLIST_BUTTON);

            Response invResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/Invoices/") && response.status() == 200,
                    addToReviewQuequeLocator::click
            );
            status = invResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Checklist Reject", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Reject function: {}", exception.getMessage());
        }
        return status;
    }
}