package com.source.classes.requisition.suspend;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrBuyerSuspend;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrBuyerSuspend.*;

public class BuyerSuspend implements IPrBuyerSuspend {

    private ILogin iLogin;
    private ILogout iLogout;
    private JsonNode jsonNode;
    private Page page;
    Logger logger;

    private BuyerSuspend(){
    }

//TODO Constructor
    public BuyerSuspend(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void suspend(){
        try {
        String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
        String title = jsonNode.get("requisition").get("title").asText();
        String remarks = jsonNode.get("commonRemarks").get("suspendRemarks").asText();

        iLogin.performLogin(buyerMailId);

        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        titleLocator.first().click();

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        suspendButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS);
        remarksLocator.fill(remarks + " " + "by" + " " + buyerMailId);

        Locator yesButtonLocator = page.locator(YES);
        yesButtonLocator.click();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Error in Requisition Buyer Suspend Function: {}", exception.getMessage());
        }
    }
}