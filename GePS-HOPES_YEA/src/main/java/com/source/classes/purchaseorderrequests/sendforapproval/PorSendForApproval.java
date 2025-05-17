package com.source.classes.purchaseorderrequests.sendforapproval;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
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
    String appUrl;

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
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int sendForApproval(String type, String purchaseType) {
        int status = 0;
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

            String porType;
            if(type.equalsIgnoreCase("sales")){
                porType = "/api/PurchaseOrderRequestsSales/";
            } else if(type.equalsIgnoreCase("ps")){
                porType = "/api/PurchaseOrderRequests/";
            } else {
                porType = "/api/PurchaseOrderRequestsNonPOC/";
            }

            Response sendForApprovalResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + porType) && response.status() == 200,
                    sendForApprovalButtonLocator::click
            );
            status = sendForApprovalResponse.status();

            page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator approvalPopupLocator = page.locator(APPROVAL_POP_UP);

            try {
                approvalPopupLocator.first().waitFor(
                        new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2000));
                if (approvalPopupLocator.first().isEnabled()) {
                    String cfoMailId = jsonNode.get("mailIds").get("cfoEmail").asText();
                    Locator cfoDropdownLocator1 = page.locator(CFO1_DROPDOWN_LOCATOR);
                    Locator cfoDropdownLocator2 = page.locator(CFO2_DROPDOWN_LOCATOR);
                    String presidentMailId = jsonNode.get("mailIds").get("presidentDirectorCorporateEmail").asText();
                    Locator presidentDropdownLocator = page.locator(PRESIDENT_DROPDOWN_LOCATOR);

                    if (cfoDropdownLocator1.count() > 0 && cfoDropdownLocator1.isEnabled() && cfoDropdownLocator1.isVisible()) {
                        cfoDropdownLocator1.click();
                        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                        searchFieldLocator.fill(cfoMailId);
                        Locator cfoIdLocator = page.locator(getCfoId(cfoMailId));
                        cfoIdLocator.click();
                    }
                    if (cfoDropdownLocator2.count() > 0 && cfoDropdownLocator2.isEnabled() && cfoDropdownLocator2.isVisible()) {
                        cfoDropdownLocator2.click();
                        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                        searchFieldLocator.fill(cfoMailId);
                        Locator cfoIdLocator = page.locator(getCfoId(cfoMailId));
                        cfoIdLocator.click();
                    }
                    if (presidentDropdownLocator.count() > 0 && presidentDropdownLocator.isEnabled() && presidentDropdownLocator.isVisible()) {
                        presidentDropdownLocator.click();
                        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                        searchFieldLocator.fill(presidentMailId);
                        Locator presidentIdLocator = page.locator(getPresidentId(presidentMailId));
                        presidentIdLocator.click();
                    }

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
                    submitButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);
                }
            } catch (Exception exception) {
                System.out.println("Approval Popup not found");
            }

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
        return status;
    }
}