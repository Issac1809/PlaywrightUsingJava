package com.source.classes.workorder.create;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.workorders.IWoCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.inspections.LInsCreate.getTitle;
import static com.constants.workorders.LWoCreate.*;
import static com.utils.SaveToTestDataJsonUtil.saveReferenceIdFromResponse;

public class WoCreate implements IWoCreate {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;

    private WoCreate(){
    }

//TODO Constructor
    public WoCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(WoCreate.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int create() {
        int status = 0;
        try {
            String logisticsManager = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManager);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String dnRefId = jsonNode.get("dispatchNotes").get("dispatchNoteReferenceId").asText();
            Locator dnTitle = page.locator(getTitle(dnRefId));
            dnTitle.click();

            Locator dropDownLocator = page.locator(ACTION_DROPDOWN);
            dropDownLocator.click();

            Locator createWorkOrderButtonLocator = page.locator(CREATE_WORK_ORDER_BUTTON);
            createWorkOrderButtonLocator.click();

            Locator selectFreightForwarderLocator = page.locator(FREIGHT_FORWARDER_DROPDOWN);
            selectFreightForwarderLocator.first().click();

            String vendorId = jsonNode.get("freightForwarderRequests").get("freightForwarder").asText();
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

            Response dnResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/WorkOrder/Listing") && response.status() == 200,
                    acceptLocator.first()::click
            );

            String poNumber = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            // Locate the row containing the dynamic poReferenceId and click the <a> tag
            Locator rows = page.locator("#listContainer tr");
            int rowCount = rows.count();
            for (int i = 0; i < rowCount; i++) {
                Locator row = rows.nth(i);
                String referenceText = row.locator("td:nth-child(3)").innerText();
                if (referenceText.contains(poNumber)) {
                    Response woResponse = page.waitForResponse(
                            response -> response.url().startsWith(appUrl + "/api/WorkOrder/") && response.status() == 200,
                            row.locator("a").first()::click
                    );
                    saveReferenceIdFromResponse(woResponse, "workOrders", "workOrderReferenceId");

                    status = woResponse.status();
                    break;
                }
            }

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Create function: {}", exception.getMessage());
        }
        return status;
    }
}