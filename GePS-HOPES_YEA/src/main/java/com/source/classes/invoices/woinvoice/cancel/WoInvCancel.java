package com.source.classes.invoices.woinvoice.cancel;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.invoices.woinvoices.IWoInvCancel;
import com.source.interfaces.invoices.woinvoices.IWoInvCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.invoices.woinvoice.LInvCancel.*;

public class WoInvCancel implements IWoInvCancel {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    IWoInvCreate iWoInvCreate;
    String appUrl;

    private WoInvCancel(){
    }

//TODO Constructor
    public WoInvCancel(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IWoInvCreate iWoInvCreate){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iWoInvCreate = iWoInvCreate;
        this.logger = LoggerUtil.getLogger(WoInvCancel.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int cancel(){
        int status = 0;
        try {
            String buyerMailId = jsonNode.get("mailIds").get("financeCheckerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            String woInvoiceRefId = jsonNode.get("invoices").get("workOrderInvoiceReferenceId").asText();
            Locator invoiceTitle = page.locator(getTitle(woInvoiceRefId));
            invoiceTitle.click();

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            suspendButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Cancelled");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);

            Response invoiceResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/Invoices/") && response.status() == 200,
                    acceptButtonLocator::click
            );
            status = invoiceResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Cancel", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Cancel function: {}", exception.getMessage());
        }
        return status;
    }
}