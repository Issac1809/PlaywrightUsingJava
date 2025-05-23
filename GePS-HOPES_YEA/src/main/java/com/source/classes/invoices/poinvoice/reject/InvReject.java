package com.source.classes.invoices.poinvoice.reject;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.invoices.poinvoices.IInvReject;
import com.source.interfaces.invoices.poinvoices.IInvSendForApproval;
import com.source.interfaces.invoices.poinvoices.IInvVerify;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.invoices.poinvoice.LInvReject.*;
import static com.constants.requisitions.LPrApprove.getTitle;


public class InvReject implements IInvReject {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    IInvVerify iInvVerify;
    IInvSendForApproval iInvSendForApproval;

    private InvReject(){
    }

//TODO Constructor
    public InvReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IInvSendForApproval iInvSendForApproval, IInvVerify iInvVerify){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iInvSendForApproval = iInvSendForApproval;
        this.iInvVerify = iInvVerify;
        this.logger = LoggerUtil.getLogger(InvReject.class);
    }

    public int reject(String referenceId, String transactionId, String uid){
        int status = 0;
        try {
            String appUrl = jsonNode.get("configSettings").get("appUrl").asText();
            String approver = jsonNode.get("invoices").get("nextApprover").asText();
            iLogin.performLogin(approver);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            Locator invoiceTitle = page.locator(getTitle(referenceId));
            invoiceTitle.click();

            Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
            rejectButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Rejected");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);

            Response invoiceResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/Invoices/") && response.status() == 200,
                    acceptButtonLocator::click);

            status = invoiceResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Reject", page);

            iLogout.performLogout();

            iInvVerify.verify(referenceId, transactionId, uid);
            iInvSendForApproval.sendForApproval(referenceId, transactionId, uid);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Reject function: {}", exception.getMessage());
        }
        return status;
    }
}