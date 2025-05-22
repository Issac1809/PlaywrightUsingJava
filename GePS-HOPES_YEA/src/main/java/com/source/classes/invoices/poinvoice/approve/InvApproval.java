package com.source.classes.invoices.poinvoice.approve;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.invoices.poinvoices.IInvApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.invoices.poinvoice.LInvApproval.*;
import static com.constants.orderschedules.LOsEdit.getTitle;

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

    public int approval(String referenceId, String transactionId, String uid){
        int status = 0;
        try {
            String appUrl = jsonNode.get("configSettings").get("appUrl").asText();
            String financeChecker = jsonNode.get("mailIds").get("financeCheckerEmail").asText();
            iLogin.performLogin(financeChecker);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            Locator invoiceTitle = page.locator(getTitle(referenceId));
            invoiceTitle.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
            approveButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);

            Response invoiceResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/Invoices/") && response.status() == 200,
                    acceptLocator::click);

            status = invoiceResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Approve", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Approval function: {}", exception.getMessage());
        }
        return status;
    }
}