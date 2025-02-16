package com.source.classes.requisition.edit;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrEdit.*;

public class Edit implements IPrEdit {

    Logger logger;
    private ILogin iLogin;
    private ILogout iLogout;
    private JsonNode jsonNode;
    private Page page;

    private Edit(){
    }

//TODO Constructor
    public Edit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Edit.class);
    }

    public int edit(String type, String purchaseType) {
        int status = 0;
        try {
        String requesterEmailId = jsonNode.get("mailIds").get("requesterEmail").asText();

        String getTitle;
        if(type.equalsIgnoreCase("PS")){
            getTitle = purchaseType.equalsIgnoreCase("Catalog") ? "psCatalogTitle" : "psNonCatalogTitle";
        } else {
            getTitle = purchaseType.equalsIgnoreCase("Catalog") ? "salesCatalogTitle" : "salesNonCatalogTitle";
        }

        String title = jsonNode.get("requisition").get(getTitle).asText();

        iLogin.performLogin(requesterEmailId);

        String getTitleLocator = getTitle(title);
        Locator titleLocator = page.locator(getTitleLocator);
        titleLocator.first().click();

        Locator editButtonLocator = page.locator(EDIT_BUTTON);
        editButtonLocator.click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        updateButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES);
        yesButtonLocator.click();

        APIResponse updateResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions", RequestOptions.create());
        status = updateResponse.status();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Error in Requisition Edit Function: {}", exception.getMessage());
        }
        return status;
    }
}