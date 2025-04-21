package com.source.classes.requisitions.suspend;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrBuyerManagerSuspend;
import com.source.interfaces.requisitions.IPrEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrBuyerManagerSuspend.*;
import static com.constants.requisitions.LPrReject.getTitle;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class BuyerManagerSuspend implements IPrBuyerManagerSuspend {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    Page page;
    IPrEdit iPrEdit;
    private String appUrl;

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
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int suspend(String type, String purchaseType) {
        int status = 0;
        try {
            String buyerManagerMailId = jsonNode.get("mailIds").get("buyerManagerEmail").asText();
            String remarks = jsonNode.get("commonRemarks").get("suspendRemarks").asText();

            iLogin.performLogin(buyerManagerMailId);

            String getTitleFromUtil = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(getTitleFromUtil));
            titleLocator.first().click();

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            suspendButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS);
            remarksLocator.fill(remarks + " " + "by" + " " + buyerManagerMailId);

            Locator acceptLocator = page.locator(YES);

            String reqType = type.equalsIgnoreCase("PS") ? "/api/Requisitions/" : "/api/RequisitionsSales/";

            Response suspendResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                    acceptLocator::click
            );
            status = suspendResponse.status();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Error in Requisition Buyer Manager Suspend Function: {}", exception.getMessage());
        }
        return status;
    }
}