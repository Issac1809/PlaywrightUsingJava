package com.source.classes.purchaseorderrequests.revision;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorderrequests.IPorApprove;
import com.source.interfaces.purchaseorderrequests.IPorRevision;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.source.interfaces.purchaseorders.IPoSendForVendor;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorRevision.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorRevision implements IPorRevision {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IPorSendForApproval iPorSendForApproval;
    IPorApprove iPorApprove;
    IPoSendForVendor iPoSendForVendor;

    private PorRevision(){
    }

//TODO Constructor
    public PorRevision(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IPorSendForApproval iPorSendForApproval, IPorApprove iPorApprove, IPoSendForVendor iPoSendForVendor) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iPorSendForApproval = iPorSendForApproval;
        this.iPorApprove = iPorApprove;
        this.iPoSendForVendor = iPoSendForVendor;
        this.logger = LoggerUtil.getLogger(PorRevision.class);
    }

    public void porRevision(String type, String purchaseType) {
        try {
            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            porNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator revisionRequestButton = page.locator(REQUEST_REVISION_BUTTON);
            revisionRequestButton.click();

            Locator justificationRequiredTextBoxLocator = page.locator(JUSTIFICATION_REQUIRED_TEXT_BOX);
            justificationRequiredTextBoxLocator.fill("POR Revision");

            Locator submitButtonLocator = page.locator(ACCEPT_BUTTON);
            submitButtonLocator.click();

            Locator editButtonLocator = page.locator(EDIT_BUTTON);
            editButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator addAdditionalItemsLocator = page.locator(ADD_ADDITIONAL_ITEM);
            addAdditionalItemsLocator.click();

            String itemName = jsonNode.get("purchaseOrderRequests").get("additionalItemName").asText();
            String rate = jsonNode.get("purchaseOrderRequests").get("rate").asText();
            String hsCode  = jsonNode.get("purchaseOrderRequests").get("hsCode").asText();
            String make = jsonNode.get("purchaseOrderRequests").get("make").asText();
            String model = jsonNode.get("purchaseOrderRequests").get("model").asText();
            String partNumber = jsonNode.get("purchaseOrderRequests").get("partNumber").asText();
            String countryOfOrigin = jsonNode.get("purchaseOrderRequests").get("countryOfOrigin").asText();
            String leadTime = jsonNode.get("purchaseOrderRequests").get("leadTime").asText();
            String shippingPoint = jsonNode.get("purchaseOrderRequests").get("shippingPoint").asText();

            Locator additionalItemNameDropDownLocator = page.locator(ITEM_NAME_DROPDOWN);
            additionalItemNameDropDownLocator.click();

            Locator additionalItemNameSearchLocator = page.locator(ITEM_NAME_SEARCH);
            additionalItemNameSearchLocator.fill(itemName);

            String additionalItemNameSelect = getItemName(itemName);
            Locator additionalItemNameSelectLocator = page.locator(additionalItemNameSelect);
            additionalItemNameSelectLocator.click();

            Locator rateLocator = page.locator(RATE);
            rateLocator.fill(rate);

            Locator hsCodeLocator = page.locator(HS_CODE);
            hsCodeLocator.fill(hsCode);

            Locator makeLocator = page.locator(MAKE);
            makeLocator.fill(make);

            Locator modelLocator = page.locator(MODEL);
            modelLocator.fill(model);

            Locator partNumberLocator = page.locator(PART_NUMBER);
            partNumberLocator.fill(partNumber);

            Locator countryOfOriginLocator = page.locator(COUNTRY_OF_ORIGIN);
            countryOfOriginLocator.fill(countryOfOrigin);

            Locator leadTimeLocator = page.locator(LEAD_TIME);
            leadTimeLocator.fill(leadTime);

            Locator shippingPointDropDownLocator = page.locator(SHIPPING_POINT_DROPDOWN);
            shippingPointDropDownLocator.click();

            Locator shippingPointSearchLocator = page.locator(SHIPPING_POINT_SEARCH);
            shippingPointSearchLocator.fill(itemName);

            String shippingPointSelect = getShippingPoint(shippingPoint);
            Locator shippingPointSelectSelectLocator = page.locator(shippingPointSelect);
            shippingPointSelectSelectLocator.click();

            Locator submitButtonLocator1 = page.locator(SUBMIT_BUTTON);
            submitButtonLocator1.click();

            Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
            updateButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Request Revision", page);

            iLogout.performLogout();

            iPorSendForApproval.sendForApproval(type, purchaseType);
            iPorApprove.approve(type, purchaseType);
            iPoSendForVendor.sendPoForVendor(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Edit function: {}", exception.getMessage());
        }
    }
}