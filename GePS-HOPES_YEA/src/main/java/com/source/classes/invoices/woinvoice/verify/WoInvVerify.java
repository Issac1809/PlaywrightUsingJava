package com.source.classes.invoices.woinvoice.verify;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.woinvoices.IWoInvVerify;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvVerify.*;


public class WoInvVerify implements IWoInvVerify {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;

    private WoInvVerify(){
    }

//TODO Constructor
    public WoInvVerify(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoInvVerify.class);
    }

    public void verify(){
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

            Locator verifyButtonLocator = page.locator(VERIFY_BUTTON);
            verifyButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Verified");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Work Order Invoice Verify", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Verify function: {}", exception.getMessage());
        }
    }
}