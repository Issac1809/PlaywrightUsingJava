package com.source.classes.workorder.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.workorders.IWoCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.workorders.LWoCreate.*;

public class WoCreate implements IWoCreate {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private WoCreate(){
    }

//TODO Constructor
    public WoCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoCreate.class);
    }

    public void create() {
        try {
            String logisticsManager = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManager);

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

            Locator dropDownLocator = page.locator(ACTION_DROPDOWN);
            dropDownLocator.click();

            Locator createWorkOrderButtonLocator = page.locator(CREATE_WORK_ORDER_BUTTON);
            createWorkOrderButtonLocator.click();

            Locator selectFreightForwarderLocator = page.locator(FREIGHT_FORWARDER_DROPDOWN);
            selectFreightForwarderLocator.first().click();

            String vendorId = jsonNode.get("mailIds").get("vendorEmail").asText();
            Locator searchField = page.locator(SEARCH_FIELD);
            searchField.fill(vendorId);

            Locator getVendorLocator = page.locator(getVendor(vendorId));
            getVendorLocator.first().click();

            Locator priorityLocator = page.locator(PRIORITY_DROPDOWN);
            priorityLocator.click();

            String priority = jsonNode.get("workOrders").get("priority").asText();
            Locator highOptionLocator = page.locator(getPriority(priority));
            highOptionLocator.click();

            Locator dateLocator = page.locator(DATE_LOCATOR);
            dateLocator.last().click();

            Locator todayLocator = page.locator(TODAY);
            todayLocator.first().click();

            Locator destinationTermLocator = page.locator(DESTINATION_FIELD);
            destinationTermLocator.fill("India");

            Locator proceedButtonLocator = page.locator(PROCEED_BUTTON);
            proceedButtonLocator.click();

            Locator emailPopUpLocator = page.locator(SEND_MAIL_BUTTON);
            emailPopUpLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Create function: {}", exception.getMessage());
        }
    }
}