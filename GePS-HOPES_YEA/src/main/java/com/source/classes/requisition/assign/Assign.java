package com.source.classes.requisition.assign;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrAssign;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrAssign.*;

public class Assign implements IPrAssign {

    private JsonNode jsonNode;
    private Page page;
    private ILogin iLogin;
    private ILogout iLogout;
    private Logger logger;

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
    }

    public void buyerManagerLogin() {
        try {
            String buyerManager = jsonNode.get("mailIds").get("buyerManagerEmail").asText();
            iLogin.performLogin(buyerManager);
        } catch (Exception exception) {
            logger.error("Error in Requisition Buyer Manager Login Function: {}", exception.getMessage());
        }
    }

    public void buyerManagerAssign() {
        try {
            String title = jsonNode.get("requisition").get("orderTitle").asText();
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            String getTitle = getTitle(title);
            String getBuyerMailId = getBuyerMailId(buyerMailId);

            Locator titleLocator = page.locator(getTitle);
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

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Error in Requisition Buyer Manager Assign Function: {}", exception.getMessage());
        }
    }
}