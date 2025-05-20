package com.source.classes.dispatchnotes.assign;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.dispatchnotes.IDnAssign;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.*;

public class DnAssign implements IDnAssign {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    PlaywrightFactory playwrightFactory;

    private DnAssign(){
    }

//TODO Constructor
    public DnAssign(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(DnAssign.class);
    }

    public void assign() {
        try {
            String logisticsManagerMailId = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManagerMailId);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                    break;
                }
            }

            Locator dnReferenceId = page.locator(DISPATCH_NOTES_REFERENCE_ID);
            String getDnRefId = dnReferenceId.innerText();

            playwrightFactory.savePropertiesIntoJsonFile("dispatchNotes", "dispatchNoteReferenceId", getDnRefId);

            Locator dropDownLocator = page.locator(DROP_DOWN);
            dropDownLocator.click();

            Locator assignButtonLocator = page.locator(ASSIGN_BUTTON);
            assignButtonLocator.click();

            Locator assignDropDownLocator = page.locator(SELECT_LOGISTICS_MANAGER_DROP_DOWN);
            assignDropDownLocator.click();

            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
            searchFieldLocator.fill(logisticsManagerMailId);

            Locator getLogisticsManagerMailId = page.locator(getLogisticsManagerId(logisticsManagerMailId));
            getLogisticsManagerMailId.click();

            Locator saveButtonLocator = page.locator(SAVE_BUTTON);
            saveButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Dispatch Notes Assign", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Assign function: {}", exception.getMessage());
        }
    }
}