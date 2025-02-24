package com.source.classes.requisition.assign;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrAssign;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrAssign.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class Assign implements IPrAssign {

    private JsonNode jsonNode;
    private Page page;
    private ILogin iLogin;
    private ILogout iLogout;
    private Logger logger;
    private String appUrl;

//TODO Constructor    
    private Assign(){
    }

//TODO Constructor
    public Assign(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Assign.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int buyerManagerAssign(String type, String purchaseType) {
        int assignStatus = 0;
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            String buyerManagerMailId = jsonNode.get("mailIds").get("buyerManagerEmail").asText();
            String uid = jsonNode.get("requisition").get("requisitionUid").asText();

            iLogin.performLogin(buyerManagerMailId);

            String getTitle = getTransactionTitle(type, purchaseType);
            String getBuyerMailId = getBuyerMailId(buyerMailId);

            Locator titleLocator = page.locator(getTitle(getTitle));
            titleLocator.first().click();

            Locator assignUser = page.locator(ASSIGN_USER);
            assignUser.click();

            Locator selectAssignUser = page.locator(SELECT_ASSIGN_USER);
            selectAssignUser.click();

            Locator searchBox = page.locator(SEARCHBOX);
            searchBox.fill(buyerMailId);

            Locator buyerManager = page.locator(getBuyerMailId);
            buyerManager.first().click();

            Locator saveUser = page.locator(SAVE_USER);
            saveUser.click();
            page.waitForLoadState(LoadState.NETWORKIDLE);

            APIResponse rejectResponse = page.request().fetch(appUrl + "/api/Requisitions/" + uid, RequestOptions.create());
            assignStatus = rejectResponse.status();
            page.waitForLoadState(LoadState.NETWORKIDLE);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Error in Requisition Buyer Manager Assign Function: {}", exception.getMessage());
        }
            return assignStatus;
    }
}