package com.source.classes.workorder.edit;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.workorders.IWoEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.workorders.LWoEdit.*;

public class WoEdit implements IWoEdit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private WoEdit(){
    }

//TODO Constructor
    public WoEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoEdit.class);
    }

    public void edit() {
        try {
            String logisticsManager = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManager);

            Locator woNavigationBarLocator = page.locator(WO_NAVIGATION_BAR);
            woNavigationBarLocator.click();

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

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator updateWorkOrderButtonLocator = page.locator(UPDATE_BUTTON);
            updateWorkOrderButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES_BUTTON);
            yesButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Edit", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Create function: {}", exception.getMessage());
        }
    }
}