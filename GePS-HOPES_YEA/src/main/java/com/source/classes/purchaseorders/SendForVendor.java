package com.source.classes.purchaseorders;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorders.IPoSendForVendor;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorCreate.getTitle;
import static com.constants.purchaseorders.LPoSendForVendor.*;
import static com.utils.GetTitleUtil.getTransactionTitle;
import static com.utils.saveReferenceIdUtil.saveReferenceIdFromApiResponse;

public class SendForVendor implements IPoSendForVendor {

    Logger logger;
    JsonNode jsonNode;
    PlaywrightFactory playwrightFactory;
    ObjectMapper objectMapper;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;

    private SendForVendor(){
    }

//TODO Constructor
    public SendForVendor(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory, ObjectMapper objectMapper){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.logger = LoggerUtil.getLogger(SendForVendor.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int sendPoForVendor(String type, String purchaseType){
        int status =0;
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            String appUrl = jsonNode.get("configSettings").get("appUrl").asText();
            iLogin.performLogin(buyerMailId);

            Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
            poNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            String url = page.url();
            String[] urlArray = url.split("=");
            String getUid = urlArray[1];
            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrders", "purchaseOrderRequestUid", getUid);

            APIResponse apiResponse = page.request().fetch(appUrl + "/api/PurchaseOrders/" + getUid, RequestOptions.create());
            JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
            String poReferenceId = jsonNode.get("referenceId").asText();
            String purchaseOrderRevisionNumber = jsonNode.get("revisionNumber").asText();
            String orderScheduleStatus = "";
            if(jsonNode.get("orderSchedule").get(0) != null){
                jsonNode.get("orderSchedule").get(0).asText();
            }
            String porRevision = "true"; //TODO Set to true for revision
            saveReferenceIdFromApiResponse(apiResponse, "purchaseOrders", "purchaseOrderReferenceId");
            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrders", "porRevision", porRevision);
            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrders", "poRevisionNumber", purchaseOrderRevisionNumber);
            playwrightFactory.savePropertiesIntoJsonFile("orderSchedules", "orderScheduleStatus", orderScheduleStatus);

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator sendForVendorButtonLocator = page.locator(SEND_FOR_VENDOR_BUTTON);
            sendForVendorButtonLocator.click();

            Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);

            Response sendResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/PurchaseOrders/") && response.status() == 200,
                            emailPopUpLocator::click
            );

            status = sendResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Send For Vendor", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Send PO For Vendor function: {}", exception.getMessage());
        }
        return status;
    }
}