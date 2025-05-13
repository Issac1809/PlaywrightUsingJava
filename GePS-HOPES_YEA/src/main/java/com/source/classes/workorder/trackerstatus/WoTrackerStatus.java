package com.source.classes.workorder.trackerstatus;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.workorders.IWoTrackerStatus;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.workorders.LWoCreate.DETAILS_BUTTON;
import static com.constants.workorders.LWoCreate.LIST_CONTAINER;
import static com.constants.workorders.LWoTrackerStatus.*;

public class WoTrackerStatus implements IWoTrackerStatus {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    PlaywrightFactory playwrightFactory;

    private WoTrackerStatus(){
    }

//TODO Constructor
    public WoTrackerStatus(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(WoTrackerStatus.class);
    }

    public void trackerStatus() {
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator workOrderNavigationBarLocator = page.locator(WO_NAVIGATION_BAR);
            workOrderNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                }
            }

            Locator woReferenceId = page.locator(WO_REFERENCE_ID);
            String getWoRefId = woReferenceId.innerText();

            playwrightFactory.savePropertiesIntoJsonFile("workOrders", "workOrderReferenceId", getWoRefId);

            for (String status : STATUSES) {
                Locator datePickerLocator = page.locator(DATE_PICKER);
                datePickerLocator.click();

                Locator todayLocator = page.locator(TODAY);
                todayLocator.first().click();

                Locator statusLocator = page.locator(STATUS_CONTAINER);
                statusLocator.last().click();

                Locator statusSelectLocator = page.locator(getStatus(status));
                statusSelectLocator.click();

                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
                submitButtonLocator.click();

                Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                acceptButtonLocator.click();
            }

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Tracker Status", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Tracker Status: {}", exception.getMessage());
        }
    }
}