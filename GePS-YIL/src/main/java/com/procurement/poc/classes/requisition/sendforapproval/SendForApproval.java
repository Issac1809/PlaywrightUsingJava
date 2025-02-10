package com.procurement.poc.classes.requisition.sendforapproval;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.RequestOptions;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrSendForApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;

import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrSendForApproval.*;

public class SendForApproval implements IPrSendForApproval {

    private Page page;
    private Properties properties;
    private ILogin iLogin;
    private ILogout iLogout;
    private ObjectMapper objectMapper;
    private String url;

    private SendForApproval() {
    }

//TODO Constructor
    public SendForApproval(ILogin iLogin, Properties properties, Page page, ILogout iLogout, ObjectMapper objectMapper) {
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.objectMapper = objectMapper;
        this.url = properties.getProperty("appUrl");
    }

//    public void sendForApproval() {
//        try {
//            String title = properties.getProperty("orderTitle");
//            iLogin.performLogin(properties.getProperty("requesterEmail"));
//
//            String getTitle = getTitle(title);
//            Locator titleLocator = page.locator(getTitle);
//            waitForLocator(titleLocator);
//            titleLocator.first().click();
//
//            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON.getLocator());
//            waitForLocator(sendForApprovalButtonLocator);
//            sendForApprovalButtonLocator.click();
//
//            Locator yesButtonLocator = page.locator(YES.getLocator());
//            waitForLocator(yesButtonLocator);
//            Response response = page.waitForResponse(
//                    resp -> resp.url().startsWith(url + "/api/Approvals") && resp.status() == 200,
//                    yesButtonLocator::click
//            );
//
//            page.waitForLoadState(LoadState.NETWORKIDLE);
//
//            List<String> matchingApprovers = new ArrayList<>();
//            List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
//            String approverMailId = ".com";
//            for (String approver : approvalTable) {
//                if (approver.endsWith(approverMailId))
//                {
//                    matchingApprovers.add(approver);
//                }
//            }
//            for (int i = 1; i <= matchingApprovers.size();i++){
//                String approverEmail = matchingApprovers.get(i-1);
//                if(i<matchingApprovers.size()) {
//                    if (approverEmail.equals(matchingApprovers.get(i)))
//                        matchingApprovers.remove(i);
//                }
//                PlaywrightFactory.saveToPropertiesFile(("Approver"+i),approverEmail);
//                PlaywrightFactory.saveToPropertiesFile("ApproverCount", String.valueOf(matchingApprovers.size()));
//            }
//
//            String PRRefereneNumber = page.locator("#referenceId").textContent();
//            PlaywrightFactory.saveToPropertiesFile("PRReferenceNumber",PRRefereneNumber);
//            iLogout.performLogout();
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }

    public String sendForApproval() {
        String approverEmail = "";
        try {
            String title = properties.getProperty("currentTitle");
            iLogin.performLogin(properties.getProperty("requesterEmail"));

            String getTitle = getTitle(title);
            Locator titleLocator = page.locator(getTitle);
            waitForLocator(titleLocator);
            titleLocator.first().click();

            String PRRefereneNumber = page.locator("#referenceId").textContent();
            PlaywrightFactory.saveToPropertiesFile("PRReferenceNumber",PRRefereneNumber);

            String pageUrl = page.url();
            String[] x = pageUrl.split("=");
            String uid = x[1];

            APIResponse requisition = page.request().fetch(url + "/api/Requisitions/" + uid, RequestOptions.create());
            JsonNode requisitionJson = objectMapper.readTree(requisition.body());
            String requisitionId = requisitionJson.get("requisitionId").asText();
//            String requisitionId =  JsonParser.parseString(requisition.text()).getAsJsonObject().get("requisitionId").getAsString();

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON.getLocator());
            waitForLocator(sendForApprovalButtonLocator);
            sendForApprovalButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES.getLocator());
            waitForLocator(yesButtonLocator);

            Response approvalAPI = page.waitForResponse(
                    resp -> resp.url().startsWith(url + "/api/Approvals?entityId="+requisitionId+"&approvalTypeEnum=Requisition") && resp.status() == 200,
                    yesButtonLocator::click
            );
            JsonNode rootNode = objectMapper.readTree(approvalAPI.body());
            JsonNode approvers = rootNode.path("approvers");
            for (JsonNode approver : approvers) {
                if ("Pending".equals(approver.path("approverStatus").asText())) {
                    approverEmail = approver.path("email").asText();
                    break;
                }
            }

            statusAssertion(page, page::reload,"requisition","Pending");

            iLogout.performLogout();
        }
        catch (Exception error) {
            System.out.println(error);
        }

        return approverEmail;
    }
}