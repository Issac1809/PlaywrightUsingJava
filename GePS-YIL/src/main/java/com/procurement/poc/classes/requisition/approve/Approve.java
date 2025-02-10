package com.procurement.poc.classes.requisition.approve;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.RequestOptions;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrApprove;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.requisitions.LPrApprove.*;

public class Approve implements IPrApprove {

    private ILogin iLogin;
    private ILogout iLogout;
    private Properties properties;
    private Page page;
    private ObjectMapper objectMapper;
    private String url;

    //TODO Constructor
    public Approve(ILogin iLogin, Properties properties, Page page, ILogout iLogout, ObjectMapper objectMapper) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
        this.objectMapper = objectMapper;
        this.url = properties.getProperty("appUrl");
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
                resp -> resp.url().startsWith(url + "/Procurement/Requisitions/POC_Details") && resp.status() == 200,
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
                        resp -> resp.url().startsWith(url + "/api/Requisitions/" + uid) && resp.status() == 200,
                        page.locator(YES.getLocator())::click
                );

                JsonNode requisitionJson = objectMapper.readTree(requisition.body());
                String requisitionId = requisitionJson.get("requisitionId").asText();


                // Get Approver with pending status
                APIResponse approvalAPI = page.request().fetch(url + "/api/Approvals?entityId=" + requisitionId + "&approvalTypeEnum=Requisition", RequestOptions.create());
                JsonNode rootNode = objectMapper.readTree(approvalAPI.body());
                JsonNode approvers = rootNode.path("approvers");
                String newApproverEmail = "";
                for (JsonNode approver : approvers) {
                    if ("Pending".equals(approver.path("approverStatus").asText())) {
                        newApproverEmail = approver.path("email").asText();
                        break;
                    }
                }

                // If no approvers are found with pending status
                if (newApproverEmail.isEmpty()){
                    pending = false;
                    statusAssertion(page, page::reload,"requisition","Approved");
                }

                // If approver with pending status is found
                else {
                    approverEmail = newApproverEmail;
                    statusAssertion(page, page::reload,"requisition","Pending");
                }
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




