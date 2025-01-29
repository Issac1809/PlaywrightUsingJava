package com.ps.classes.requisition.sendforapproval;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrSendForApproval;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.constants.requisitions.LPrSendForApproval.*;

public class SendForApproval implements IPrSendForApproval {

    private PlaywrightFactory playwrightFactory;
    private ObjectMapper objectMapper;
    private Page page;
    private Properties properties;
    private ILogin iLogin;
    private ILogout iLogout;

    private SendForApproval() {
    }

//TODO Constructor
    public SendForApproval(PlaywrightFactory playwrightFactory, ObjectMapper objectMapper, ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public int sendForApproval(String purchaseType) {
        int approvalStatus = 0;
        List<String> approvers = new ArrayList<>();

        try {
        String requesterMailId = properties.getProperty("requesterEmail");

        iLogin.performLogin(requesterMailId);

        String title;
        if(purchaseType.toLowerCase().equals("catalog")){
            title = properties.getProperty("catalogTitle");
        } else {
            title = properties.getProperty("nonCatalogTitle");
        }

        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        titleLocator.first().click();

        String url = page.url();
        String[] urlArray = url.split("=");
        String getUid = urlArray[1];

        playwrightFactory.saveToPropertiesFile("requisitionUid", getUid);

        APIResponse approvalResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions/" + getUid, RequestOptions.create());
        JsonNode getApproversJson = objectMapper.readTree(approvalResponse.body());
        int requisitionId = getApproversJson.get("requisitionId").asInt();

        Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON);
        sendForApprovalButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES);
        yesButtonLocator.click();

        APIResponse approverResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Approvals?entityId=" + requisitionId + "&approvalTypeEnum=Requisition", RequestOptions.create());
        JsonNode approversJson = objectMapper.readTree(approverResponse.body());
        approvalStatus = approverResponse.status();

            if(approversJson.has("approvers")) {
                JsonNode approversArray = approversJson.get("approvers");
                for(JsonNode approver : approversArray){
                    if(approver.has("email")){
                        String finalApprover = approver.get("email").asText();
                        approvers.add(finalApprover);
                    }
                }
            }

            if(!approvers.isEmpty()){
                for (String approver : approvers) {
                    playwrightFactory.saveToPropertiesFile("requisitionApprovers", approver);
                }
            }

            page.waitForLoadState(LoadState.NETWORKIDLE);

            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("Error in Requisition Send For Approval Function: " + error.getMessage());
        }
        return approvalStatus;
    }
}