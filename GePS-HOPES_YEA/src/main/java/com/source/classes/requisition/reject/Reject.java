package com.source.classes.requisition.reject;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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

    private Reject(){
    }

//TODO Constructor
    public Reject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Reject.class);
    }

    public int reject(String type, String purchaseType) {
        int rejectStatus = 0;
        try {
            String[] approvers = jsonNode.get("requisition").get("requisitionApprovers").asText().split(",");
            String uid = jsonNode.get("requisition").get("requisitionUid").asText();
            String remarks = jsonNode.get("commonRemarks").get("rejectRemarks").asText();

            for(String approver : approvers){
                iLogin.performLogin(approver);

                String getTitleFromUtil = getTransactionTitle(type, purchaseType);
                Locator titleLocator = page.locator(getTitle(getTitleFromUtil));
                titleLocator.first().click();

                Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
                rejectButtonLocator.click();

                Locator rejectRemarksLocator = page.locator(REJECTED_REMARKS);
                rejectRemarksLocator.fill(remarks + " " + "by" + " " + approver);

                Locator yesButtonLocator = page.locator(SUBMIT_BUTTON);
                yesButtonLocator.click();

                APIResponse rejectResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions/" + uid, RequestOptions.create());
                rejectStatus = rejectResponse.status();
                break;
            }

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Requisition Reject Function: {}", exception.getMessage());
        }
        return rejectStatus;
    }
}