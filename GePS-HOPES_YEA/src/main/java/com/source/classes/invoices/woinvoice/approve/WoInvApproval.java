package com.source.classes.invoices.woinvoice.approve;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.invoices.woinvoices.IWoInvApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvApproval.*;
import static com.constants.invoices.woinvoice.LInvChecklistReject.getTitle;
import static com.utils.SaveToTestDataJsonUtil.saveAndReturNextApprover;

public class WoInvApproval implements IWoInvApproval {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;

    private WoInvApproval(){
    }

//TODO Constructor
    public WoInvApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoInvApproval.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int approval(){
        int status = 0;
        try {
            String approver = jsonNode.get("invoices").get("nextApprover").asText();
            iLogin.performLogin(approver);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            String woReferenceId = jsonNode.get("invoices").get("workOrderInvoiceReferenceId").asText();
            Locator invoiceTitle = page.locator(getTitle(woReferenceId));
            invoiceTitle.click();

            Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
            approveButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);

            Response invoiceResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/Invoices/")
                            && response.status() == 200
                            && response.request().method().equals("GET"),
                    acceptButtonLocator::click
            );
            status = invoiceResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Approval", page);

            iLogout.performLogout();

            String nextApprover = saveAndReturNextApprover(invoiceResponse);
            if(!nextApprover.equalsIgnoreCase("")){
                approval();
            }

        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Approval function: {}", exception.getMessage());
        }
        return status;
    }
}