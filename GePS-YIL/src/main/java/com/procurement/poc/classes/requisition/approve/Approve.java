package com.procurement.poc.classes.requisition.approve;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrApprove;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.procurement.poc.constants.requisitions.LPrApprove.*;

public class Approve implements IPrApprove {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private ObjectMapper objectMapper;

    //TODO Constructor
    public Approve(ILogin iLogin, Properties properties, Page page, ILogout iLogout, ObjectMapper objectMapper) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.objectMapper = objectMapper;
    }

    public void approve() {
        try {
            int x = Integer.parseInt(properties.getProperty("ApproverCount"));
            List<String> approvers = new ArrayList<>();
            for (int i = 1; i <= x; i++) {
                approvers.add(properties.getProperty("Approver" + i));
            }
            String PRReferenceNumber = properties.getProperty("PRReferenceNumber");
            for (String approverMailId : approvers) {
                ApproveMethod(approverMailId, PRReferenceNumber);
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public void ApproveMethod(String ApproverMailId, String PRReferenceNumber) throws InterruptedException {
        iLogin.performLogin(ApproverMailId);
        page.locator(MY_APPROVALS.getLocator()).click();
        String title = "Requisition " + PRReferenceNumber;
        page.locator(getString(title)).first().click();
        page.locator(APPROVE.getLocator()).click();
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/Procurement/Requisitions/POC_Details") && resp.status() == 200,
                page.locator(YES.getLocator())::click
        );
        iLogout.performLogout();
    }

    public void completeApprove(String email) {
        String PRReferenceNumber = properties.getProperty("PRReferenceNumber");
        String approverEmail = email;
        boolean pending = true;
        while (pending) {
            try {
                iLogin.performLogin(approverEmail);
                page.locator(MY_APPROVALS.getLocator()).click();
                String title = "Requisition " + PRReferenceNumber;
                page.locator(getString(title)).first().click();
                String uid = getUID(page);

                page.locator(APPROVE.getLocator()).click();


                Response requisition = page.waitForResponse(
                        resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/api/Requisitions/" + uid) && resp.status() == 200,
                        page.locator(YES.getLocator())::click
                );

//                APIResponse requisition = page.request().fetch("https://geps_hopes_yil.cormsquare.com/api/Requisitions/" + uid, RequestOptions.create());
                JsonNode requisitionJson = objectMapper.readTree(requisition.body());
                String requisitionId = requisitionJson.get("requisitionId").asText();

                APIResponse approvalAPI = page.request().fetch("https://geps_hopes_yil.cormsquare.com/api/Approvals?entityId=" + requisitionId + "&approvalTypeEnum=Requisition", RequestOptions.create());
                JsonNode rootNode = objectMapper.readTree(approvalAPI.body());
                JsonNode approvers = rootNode.path("approvers");
                String newApproverEmail = "";
                for (JsonNode approver : approvers) {
                    if ("Pending".equals(approver.path("approverStatus").asText())) {
                        newApproverEmail = approver.path("email").asText();
                        break;
                    }
                }
                if (newApproverEmail.isEmpty())
                    pending = false;
                else
                    approverEmail = newApproverEmail;
                iLogout.performLogout();
            }
            catch (Exception error) {
                System.out.println(error);
            }
        }
    }

    public String getUID(Page page){
        String url = page.url();
        String[] x = url.split("=");
        String uid = x[1];
        return uid;
    }
}




