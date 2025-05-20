package com.source.classes.invoices.poinvoice.cancel;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.invoices.poinvoices.IInvCancel;
import com.source.interfaces.invoices.poinvoices.IInvCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.LIST_CONTAINER;
import static com.constants.invoices.poinvoice.LInvApproval.INVOICE_SELECT;
import static com.constants.invoices.poinvoice.LInvCancel.*;

public class InvCancel implements IInvCancel {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    IInvCreate iInvCreate;

    private InvCancel(){
    }

//TODO Constructor
    public InvCancel(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IInvCreate iInvCreate){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iInvCreate = iInvCreate;
        this.logger = LoggerUtil.getLogger(InvCancel.class);
    }

    public void cancel(String type){
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

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            suspendButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Cancelled");

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Invoice Cancel", page);

            iLogout.performLogout();

            iInvCreate.invoiceTypeHandler(type);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Cancel function: {}", exception.getMessage());
        }
    }
}