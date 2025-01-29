package com.ps.classes.requisition.approve;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.ps.interfaces.login.ILogin;
import com.ps.interfaces.logout.ILogout;
import com.ps.interfaces.requisitions.IPrApprove;
import java.util.Properties;
import static com.constants.requisitions.LPrApprove.*;

public class Approve implements IPrApprove {

    private ObjectMapper objectMapper;
    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;

    private Approve(){
    }

//TODO Constructor
    public Approve(ObjectMapper objectMapper, ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.objectMapper = objectMapper;
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public int approve() {
        int status = 0;
        try {
            String[] approvers = properties.getProperty("requisitionApprovers").split(",");
            String uid = properties.getProperty("requisitionUid");

            for(String approver : approvers) {
                iLogin.performLogin(approver);

                String title = properties.getProperty("orderTitle");
                String transactionLocator = getApproveButton(title);
                Locator transaction = page.locator(transactionLocator);
                transaction.first().click();

                Locator approveButton = page.locator(APPROVE);
                approveButton.click();

                String remarks = properties.getProperty("approveRemarks");
                Locator approveRemarksLocator = page.locator(APPROVE_REMARKS);
                approveRemarksLocator.fill(remarks + " " + "by" + " " + approver);

                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
                submitButtonLocator.click();

                APIResponse statusResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/Requisitions/" + uid, RequestOptions.create());
                status = statusResponse.status();
                JsonNode responseJson = objectMapper.readTree(statusResponse.body());

                String requisitionStatus = "";
                if(responseJson.has("status")) {
                    requisitionStatus = responseJson.get("status").asText();
                    break;
                }

                page.waitForLoadState(LoadState.NETWORKIDLE);

                iLogout.performLogout();

                if(requisitionStatus.equals("Approved")) {
                    break;
                }
            }
        } catch (Exception error) {
            System.out.println("Error in Requisition Approve Function: " + error.getMessage());
        }
        return status;
    }
}