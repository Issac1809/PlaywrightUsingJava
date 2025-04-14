package com.source.classes.orderschedules.reject;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.orderschedules.IOsReject;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.orderschedules.LOsReject.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class OsReject implements IOsReject {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsReject(){
    }

//TODO Constructor
    public OsReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(OsReject.class);
    }

    public void reject(String type, String purchaseType){
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

            Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
            rejectButtonLocator.click();

            Locator remarksInoutLocator = page.locator(REMARKS_INPUT);
            remarksInoutLocator.fill("Rejected");

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in OS Reject Function: {}", exception.getMessage());
        }
    }
}