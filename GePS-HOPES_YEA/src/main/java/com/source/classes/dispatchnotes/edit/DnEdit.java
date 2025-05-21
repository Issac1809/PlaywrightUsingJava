package com.source.classes.dispatchnotes.edit;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.dispatchnotes.IDnEdit;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnEdit.*;
import static com.constants.dispatchnotes.LDnReturn.ACCEPT_BUTTON;
import static com.constants.inspections.LInsCreate.getTitle;
import static com.utils.SaveToTestDataJsonUtil.saveReferenceIdFromResponse;

public class DnEdit implements IDnEdit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;

    private DnEdit(){
    }

//TODO Constructor
    public DnEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(DnEdit.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int edit() {
        int status = 0;
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String dnRefId = jsonNode.get("dispatchNotes").get("dispatchNoteReferenceId").asText();
            Locator dnTitle = page.locator(getTitle(dnRefId));
            dnTitle.click();

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            editButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
            updateButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);

            Response dnResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/VP/DispatchNotes/Listing") && response.status() == 200,
                    acceptLocator.first()::click
            );
            status = dnResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Dispatch Notes Edit", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Edit function: {}", exception.getMessage());
        }
        return status;
    }
}