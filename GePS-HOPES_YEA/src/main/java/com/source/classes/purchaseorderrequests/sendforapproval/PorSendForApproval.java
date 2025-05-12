package com.source.classes.purchaseorderrequests.sendforapproval;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorSendForApproval.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorSendForApproval implements IPorSendForApproval {

    Logger logger;
    ObjectMapper objectMapper;
    PlaywrightFactory playwrightFactory;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorSendForApproval() {
    }

//TODO Constructor
    public PorSendForApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, ObjectMapper objectMapper, PlaywrightFactory playwrightFactory) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.objectMapper = objectMapper;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(PorSendForApproval.class);
    }

    public String sendForApproval(String type, String purchaseType) {
        String email = "";
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            iLogin.performLogin(buyerMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            porNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL__BUTTON);
            sendForApprovalButtonLocator.click();

            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator approvalPopupLocator = page.locator(APPROVAL_POP_UP);

            if(approvalPopupLocator.first().isEnabled() && approvalPopupLocator.first().isVisible() || !approvalPopupLocator.first().isHidden()) {
                String cfoMailId = jsonNode.get("mailIds").get("cfoEmail").asText();
                Locator cfoDropdownLocator = page.locator(CFO_DROPDOWN_LOCATOR);
                String presidentMailId = jsonNode.get("mailIds").get("presidentDirectorCorporateEmail").asText();
                Locator presidentDropdownLocator = page.locator(PRESIDENT_DROPDOWN_LOCATOR);

                if (cfoDropdownLocator.isVisible()) {
                    cfoDropdownLocator.click();
                    Locator cfoIdLocator = page.locator(getCfoId(cfoMailId));
                    cfoIdLocator.click();
                } if (presidentDropdownLocator.isVisible()) {
                    presidentDropdownLocator.click();
                    Locator presidentIdLocator = page.locator(getPresidentId(presidentMailId));
                    presidentIdLocator.click();
                }

                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
                submitButtonLocator.click();

                page.waitForLoadState(LoadState.NETWORKIDLE);
            }
            page.waitForLoadState(LoadState.NETWORKIDLE);

            String appUrl = jsonNode.get("configSettings").get("appUrl").asText();
            String id = jsonNode.get("purchaseOrderRequests").get("purchaseOrderRequestId").asText();

            APIResponse apiResponse = page.request().fetch(appUrl + "/api/Approvals?entityId=" + id + "&approvalTypeEnum=PurchaseOrderRequest");
            JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
            JsonNode approvers = jsonNode.get("approvers");

            for(JsonNode approver : approvers){
                email = approver.get("email").asText();
                break;
            }

            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrderRequests", "approvers", email);

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Request Send For Approval", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Send For Approval function: {}", exception.getMessage());
        }
        return email;
    }
}