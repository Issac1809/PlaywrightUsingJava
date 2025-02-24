package com.source.classes.requisition.approve;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requisitions.IPrApprove;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requisitions.LPrApprove.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class Approve implements IPrApprove {

    private Logger logger;
    private ObjectMapper objectMapper;
    private ILogin iLogin;
    private ILogout iLogout;
    private JsonNode jsonNode;
    private Page page;
    private String appUrl;

    private Approve(){
    }

//TODO Constructor
    public Approve(ObjectMapper objectMapper, ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.objectMapper = objectMapper;
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Approve.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int approve(String type, String purchaseType) {
        int status = 0;
        try {
            String requisitionStatus = "";
            String[] approvers = jsonNode.get("requisition").get("requisitionApprovers").asText().split(",");
            String uid = jsonNode.get("requisition").get("requisitionUid").asText();
            String remarks = jsonNode.get("commonRemarks").get("approveRemarks").asText();

            for(String approver : approvers) {
                iLogin.performLogin(approver);

                String title = getTransactionTitle(type, purchaseType);
                Locator transaction = page.locator(getTitle(title));
                transaction.first().click();

                Locator approveButton = page.locator(APPROVE);
                approveButton.click();

                Locator approveRemarksLocator = page.locator(APPROVE_REMARKS);
                approveRemarksLocator.fill(remarks + " " + "by" + " " + approver);

                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
                submitButtonLocator.click();
                page.waitForLoadState(LoadState.NETWORKIDLE);

                APIResponse statusResponse = page.request().fetch(appUrl + "/api/Requisitions/" + uid, RequestOptions.create());
                status = statusResponse.status();
                JsonNode responseJson = objectMapper.readTree(statusResponse.body());

                if(responseJson.has("status")) {
                    requisitionStatus = responseJson.get("status").asText();
                }

                iLogout.performLogout();

                if(requisitionStatus.equals("Approved")) {
                    break;
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Requisition Approve Function: {}", exception.getMessage());
        }
        return status;
    }
}