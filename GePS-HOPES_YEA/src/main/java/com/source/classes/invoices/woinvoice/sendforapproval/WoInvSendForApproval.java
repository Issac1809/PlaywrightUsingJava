package com.source.classes.invoices.woinvoice.sendforapproval;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.woinvoices.IWoInvSendForApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvSendForApproval.*;


public class WoInvSendForApproval implements IWoInvSendForApproval {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvSendForApproval(){
    }

//TODO Constructor
    public WoInvSendForApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoInvSendForApproval.class);
    }

    public void sendForApproval(){
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

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON);
            sendForApprovalButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Send For Approval", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Send For Approval function: {}", exception.getMessage());
        }
    }
}