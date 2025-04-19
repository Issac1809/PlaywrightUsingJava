package com.source.classes.invoices.woinvoice.invreturn;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.invoices.woinvoices.IWoInvReturn;
import com.source.interfaces.invoices.woinvoices.IWoInvSendForApproval;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.invoices.woinvoice.LInvReturn.*;

public class WoInvReturn implements IWoInvReturn {

    Logger logger;
    Page page;
    JsonNode jsonNode;
    ILogin iLogin;
    ILogout iLogout;
    IWoInvSendForApproval iWoInvSendForApproval;

    private WoInvReturn(){
    }

//TODO Constructor
    public WoInvReturn(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IWoInvSendForApproval iWoInvSendForApproval){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iWoInvSendForApproval = iWoInvSendForApproval;
        this.logger = LoggerUtil.getLogger(WoInvReturn.class);
    }

    public void returnMethod(){
        try {
            iWoInvSendForApproval.sendForApproval();

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

            Locator holdButtonLocator = page.locator(SUSPEND_BUTTON);
            holdButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Returned");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Return function: {}", exception.getMessage());
        }
    }
}