package com.source.classes.orderschedules.edit;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.orderschedules.IOsEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;

import static com.constants.orderschedules.LOsEdit.*;
import static com.utils.saveReferenceIdUtil.saveReferenceIdFromResponse;

public class OsEdit implements IOsEdit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;

    private OsEdit(){
    }

//TODO Constructor
    public OsEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(OsEdit.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int edit(){
        int status =0;
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator osNavigationBarLocator = page.locator(OS_NAVIGATION_BAR);
            osNavigationBarLocator.click();

            String osRefId = jsonNode.get("orderSchedules").get("orderScheduleReferenceId").asText();
            Locator osTitle = page.locator(getTitle(osRefId));
            osTitle.click();

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            editButtonLocator.click();

            Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
            updateButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            Response createResponse = page.waitForResponse(
                    response -> response.url().equals(appUrl + "/api/VP/OrderSchedules/Listing") && response.status() == 200,
                    acceptButtonLocator.first()::click
            );

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            Locator rows = page.locator("#listContainer tr");
            int rowCount = rows.count();
            for (int i = 0; i < rowCount; i++) {
                Locator row = rows.nth(i);
                String referenceText = row.locator("td:nth-child(3)").innerText();
                if (referenceText.contains(poReferenceId)) {
                    Response osResponse = page.waitForResponse(
                            response -> response.url().startsWith(appUrl + "/api/VP/OrderSchedules/") && response.status() == 200,
                            row.locator("a").first()::click
                    );
                    saveReferenceIdFromResponse(osResponse, "orderSchedules", "orderScheduleReferenceId");

                    status = osResponse.status();
                    break;
                }
            }

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Order Schedule Edit", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in OS Edit Function: {}", exception.getMessage());
        }
        return status;
    }
}