package com.source.classes.orderschedules.approve;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.orderschedules.IOsApprove;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.orderschedules.LOsApprove.*;
import static com.constants.orderschedules.LOsReject.getTitle;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class OsApprove implements IOsApprove {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsApprove(){
    }

//TODO Constructor
    public OsApprove(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(OsApprove.class);
    }

    public void approve(String type, String purchaseType){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
            poNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator viewOrderScheduleButtonLocator = page.locator(VIEW_ORDER_SCHEDULE__BUTTON);
            viewOrderScheduleButtonLocator.click();

            Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
            approveButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Order Schedule Approve", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in OS Approve function: {}", exception.getMessage());
        }
    }
}