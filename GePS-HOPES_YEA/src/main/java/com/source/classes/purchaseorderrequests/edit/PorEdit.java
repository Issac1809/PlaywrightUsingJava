package com.source.classes.purchaseorderrequests.edit;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.purchaseorderrequests.IPorEdit;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorEdit.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorEdit implements IPorEdit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorEdit(){
    }

//TODO Constructor
    public PorEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(PorEdit.class);
    }

    public void porEdit(String type, String purchaseType) {
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            iLogin.performLogin(buyerMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            porNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            editButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
            updateButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Updated");

            Locator acceptLocator = page.locator(YES);
            acceptLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Request Edit", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Edit function: {}", exception.getMessage());
        }
    }
}