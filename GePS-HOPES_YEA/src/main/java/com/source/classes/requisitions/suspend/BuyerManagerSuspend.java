package com.source.classes.requisitions.suspend;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrBuyerManagerSuspend;
import com.source.interfaces.requisitions.IPrEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrBuyerManagerSuspend.*;

public class BuyerManagerSuspend implements IPrBuyerManagerSuspend {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    Page page;
    IPrEdit iPrEdit;

    private BuyerManagerSuspend(){
    }

//TODO Constructor
    public BuyerManagerSuspend(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IPrEdit iPrEdit){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iPrEdit = iPrEdit;
        this.logger = LoggerUtil.getLogger(BuyerManagerSuspend.class);
    }

    public void suspend() {
        try {
            String buyerManagerMailId = jsonNode.get("mailIds").get("BuyerManager").asText();
            String title = jsonNode.get("requisition").get("title").asText();
            String remarks = jsonNode.get("commonRemarks").get("suspendRemarks").asText();

            iLogin.performLogin(buyerManagerMailId);

            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            suspendButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS);
            remarksLocator.fill(remarks + " " + "by" + " " + buyerManagerMailId);

            Locator acceptLocator = page.locator(YES);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Error in Requisition Buyer Manager Suspend Function: {}", exception.getMessage());
        }
    }
}