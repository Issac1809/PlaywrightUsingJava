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
import java.util.List;

import static com.constants.orderschedules.LOsCreate.DETAILS_BUTTON;
import static com.constants.orderschedules.LOsCreate.LIST_CONTAINER;
import static com.constants.orderschedules.LOsEdit.*;

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

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                    break;
                }
            }

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            editButtonLocator.click();

            Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
            updateButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            Response createResponse = page.waitForResponse(
                    response -> response.url().equals(appUrl + "/api/VP/OrderSchedules/Listing") && response.status() == 200,
                    acceptButtonLocator.first()::click
            );

            String poNumber = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList1 = page.locator(LIST_CONTAINER).allTextContents();
            for (String tr : containerList1) {
                if (tr.contains(poNumber)) {
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);

                    Response osResponse = page.waitForResponse(
                            response -> response.url().startsWith(appUrl + "/api/VP/OrderSchedules/") && response.status() == 200,
                            detailsButtonLocator.first()::click
                    );
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