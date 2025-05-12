package com.source.classes.invoices.woinvoice.hold;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.woinvoices.IWoInvHold;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvHold.*;


public class WoInvHold implements IWoInvHold {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvHold(){
    }

//TODO Constructor
    public WoInvHold(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoInvHold.class);
    }

    public void hold(){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator invoiceNaviagtionBarLocator = page.locator(INVOICE_NAVIGATION_BAR);
            invoiceNaviagtionBarLocator.click();

            String woReferenceId = jsonNode.get("workOrders").get("workOrderReferenceId").asText();
            List<String> invoiceContainer = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : invoiceContainer){
                if (tr.contains(woReferenceId)){
                    Locator invoiceSelectLocator = page.locator(INVOICE_SELECT);
                    invoiceSelectLocator.first().click();
                }
                break;
            }

            Locator holdButtonLocator = page.locator(HOLD_BUTTON);
            holdButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Hold");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Hold", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Hold function: {}", exception.getMessage());
        }
    }
}