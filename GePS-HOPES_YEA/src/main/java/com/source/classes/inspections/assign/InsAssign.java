package com.source.classes.inspections.assign;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.inspections.IInsAssign;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.inspections.LInsAssign.*;

public class InsAssign implements IInsAssign {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private InsAssign() {
    }

//TODO Constructor
    public InsAssign(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(InsAssign.class);
    }

    public void assign() {
        try {
            String mailId = jsonNode.get("mailIds").get("requesterEmail").asText();
            iLogin.performLogin(mailId);

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

            Locator assignButtonLocator = page.locator(ASSIGN_INSPECTOR_BUTTON);
            assignButtonLocator.click();

            Locator dropDownLocator = page.locator(SELECT_INSPECTOR_DROP_DOWN);
            dropDownLocator.click();

            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
            searchFieldLocator.fill(mailId);

            Locator requesterMailIdLocator = page.locator(getRequestorId(mailId));
            requesterMailIdLocator.first().click();

            Locator saveInspectorButtonLocator = page.locator(SAVE_INSPECTOR);
            saveInspectorButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Inspection Assign", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Inspection Assign function: {}", exception.getMessage());
        }
    }
}