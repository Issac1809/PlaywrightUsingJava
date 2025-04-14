package com.source.classes.orderschedules.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.orderschedules.IOsCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.orderschedules.LOsCreate.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class OsCreate implements IOsCreate {

    Logger logger;
    JsonNode jsonNode;
    PlaywrightFactory playwrightFactory;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsCreate() {
    }

//TODO Constructor
    public OsCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(OsCreate.class);
    }

    public void create(String type, String purchaseType) {
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator porNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
            porNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator poReferenceId = page.locator(PO_REFERENCE_ID);
            String getPoRefId = poReferenceId.textContent();

            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrders", "poReferenceId", getPoRefId);

            Locator createOsButtonLocator = page.locator(CREATE_OS_BUTTON);
            createOsButtonLocator.click();

            Locator orderScheduleDate = page.locator(SCHEDULE_DATE);
            orderScheduleDate.last().click();

            Locator todayLocator = page.locator(TODAY);
            todayLocator.first().click();

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in OS Create Function: {}", exception.getMessage());
        }
    }
}