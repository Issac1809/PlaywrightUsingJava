package com.source.classes.dispatchnotes.edit;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.dispatchnotes.IDnEdit;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnEdit.*;
import static com.constants.dispatchnotes.LDnReturn.ACCEPT_BUTTON;

public class DnEdit implements IDnEdit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private DnEdit(){
    }

//TODO Constructor
    public DnEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(DnEdit.class);
    }

    public void edit() {
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                }
            }

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            editButtonLocator.click();

            Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
            updateButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Dispatch Notes Edit", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Edit function: {}", exception.getMessage());
        }
    }
}