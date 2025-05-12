package com.source.classes.invoices.woinvoice.cancel;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.woinvoices.IWoInvCancel;
import com.source.interfaces.invoices.woinvoices.IWoInvCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvApproval.INVOICE_SELECT;
import static com.constants.invoices.woinvoice.LInvApproval.LIST_CONTAINER;
import static com.constants.invoices.woinvoice.LInvCancel.*;

public class WoInvCancel implements IWoInvCancel {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    IWoInvCreate iWoInvCreate;

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
    }

    public void cancel(){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator invoiceNavigationBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNavigationBarLocator.click();

            String woReferenceId = jsonNode.get("workOrders").get("workOrderReferenceId").asText();
            List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : invoiceContainer){
                if (tr.contains(woReferenceId)){
                    Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
                    invoiceSelectLocator.first().click();
                }
                break;
            }

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            suspendButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Cancelled");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Cancel", page);

            iLogout.performLogout();

            iWoInvCreate.create();
            double finalGSTPercentage = iWoInvCreate.gst();
            iWoInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Cancel function: {}", exception.getMessage());
        }
    }
}