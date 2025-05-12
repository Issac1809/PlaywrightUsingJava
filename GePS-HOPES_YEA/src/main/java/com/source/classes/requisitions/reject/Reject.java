package com.source.classes.requisitions.reject;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrReject;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrReject.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class Reject implements IPrReject {

    Logger logger;
    private ILogin iLogin;
    private ILogout iLogout;
    private Page page;
    private JsonNode jsonNode;
    private String appUrl;

    private Reject(){
    }

//TODO Constructor
    public Reject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Reject.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int reject(String type, String purchaseType) {
        int rejectStatus = 0;
        String uid = jsonNode.get("requisition").get("requisitionUid").asText();
        try {
            String[] approvers = jsonNode.get("requisition").get("requisitionApprovers").asText().split(",");
            String remarks = jsonNode.get("commonRemarks").get("rejectRemarks").asText();

            String approver1 = approvers[0];
            iLogin.performLogin(approver1);

            String getTitleFromUtil = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(getTitleFromUtil));
            titleLocator.first().click();

            Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
            rejectButtonLocator.click();

            Locator rejectRemarksLocator = page.locator(REJECTED_REMARKS);
            rejectRemarksLocator.fill(remarks + " " + "by" + " " + approver1);

            Locator yesButtonLocator = page.locator(SUBMIT_BUTTON);

            String reqType = type.equalsIgnoreCase("PS") ? "/api/Requisitions/" : "/api/RequisitionsSales/";

            Response rejectResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                    yesButtonLocator::click
            );
            rejectStatus = rejectResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Requisition Reject", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Requisition Reject Function: {}", exception.getMessage());
        }

        return rejectStatus;
    }
}