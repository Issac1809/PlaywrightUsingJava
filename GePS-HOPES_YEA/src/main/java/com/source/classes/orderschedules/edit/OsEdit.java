package com.source.classes.orderschedules.edit;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.orderschedules.IOsEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.orderschedules.LOsEdit.*;

public class OsEdit implements IOsEdit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private OsEdit(){
    }

//TODO Constructor
    public OsEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(OsEdit.class);
    }

    public void edit(){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator osNavigationBarLocator = page.locator(OS_NAVIGATION_BAR);
            osNavigationBarLocator.click();

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

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in OS Edit Function: {}", exception.getMessage());
        }
    }
}