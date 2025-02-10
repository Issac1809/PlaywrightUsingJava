package com.procurement.poc.classes.purchaseorderrequest.approve;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.RequestOptions;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorApprove;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.purchaseorderrequests.LPorApprove.*;

public class PorApprove implements IPorApprove {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    ObjectMapper objectMapper;
    private String url;

    private PorApprove() {
    }

    //TODO Constructor
    public PorApprove(ILogin iLogin, Properties properties, Page page, ILogout iLogout, ObjectMapper objectMapper) {
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
            String PORReferenceNumber = properties.getProperty("PORReferenceNumber");
            for (String approverMailId : approvers) {
                approveMethod(approverMailId, PORReferenceNumber);
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public void approveMethod(String ApproverMailId, String PORReferenceNumber) {
        iLogin.performLogin(ApproverMailId);
        page.locator(MY_APPROVALS.getLocator()).click();
        String title = "PurchaseOrderRequest " + PORReferenceNumber;
        page.locator(getString(title)).first().click();
        page.locator(APPROVE_BUTTON.getLocator()).click();
        Response response = page.waitForResponse(
                resp -> resp.url().startsWith(url + "/Procurement/PurchaseOrderRequests/POC_Details?uid") && resp.status() == 200,
                page.locator(ACCEPT_BUTTON.getLocator())::click
        );
        iLogout.performLogout();
    }

    public void completeApprove(String email) {
        String PORReferenceNumber = properties.getProperty("PORReferenceNumber");
        String approverEmail = email;
        boolean pending = true;
        while (pending) {
            try {
                iLogin.performLogin(approverEmail);
                page.locator(MY_APPROVALS.getLocator()).click();
                String title = "PurchaseOrderRequest " + PORReferenceNumber;
                page.locator(getString(title)).first().click();
                String uid = getUID(page);

                page.locator(APPROVE_BUTTON.getLocator()).click();

                Response por = page.waitForResponse(
                        resp -> resp.url().startsWith(url + "/api/PurchaseOrderRequests/" + uid) && resp.status() == 200,
                        page.locator(ACCEPT_BUTTON.getLocator())::click
                );
                JsonNode porJson = objectMapper.readTree(por.body());
                String porId = porJson.get("id").asText();

                APIResponse approvalAPI = page.request().fetch(url + "/api/Approvals?entityId=" + porId + "&approvalTypeEnum=PurchaseOrderRequest", RequestOptions.create());
                JsonNode rootNode = objectMapper.readTree(approvalAPI.body());
                JsonNode approvers = rootNode.path("approvers");
                String newApproverEmail = "";
                for (JsonNode approver : approvers) {
                    if ("Pending".equals(approver.path("approverStatus").asText())) {
                        newApproverEmail = approver.path("email").asText();
                        break;
                    }
                }
                if (newApproverEmail.isEmpty()) {
                    pending = false;
//                    statusAssertion(page, page::reload, "por", "ProcessingPO");
                }
                else {
                    approverEmail = newApproverEmail;
                    statusAssertion(page, page::reload, "por", "Pending");
                }
                iLogout.performLogout();
            }
            catch (Exception e){
                System.out.println("Error: "+e);
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
//    public void approverLogin(List<String> approvers) {
//        try {
//        String cormsquareMailId = "@cormsquare.com";
//        String sharklasersMailId = "@sharklasers.com";
//        String yokogawaMailId = "@yokogawa.com";
//        List<String> groupIds = new ArrayList<>();
//        for (int i = 0; i < approvers.size(); i++) {
//            String approverMailId = approvers.get(i);
//            if (approverMailId.endsWith(cormsquareMailId) || approverMailId.endsWith(sharklasersMailId) || approverMailId.endsWith(yokogawaMailId)) {
////                iLogin.performLogin(approverMailId, page);
////TODO Approver Approves POR
//                Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
//                waitForLocator(myApprovalsButtonLocator);
//                myApprovalsButtonLocator.click();
//
//                String title = properties.getProperty("Title");
//                Locator titleLocator = page.locator(getTitle(title));
//                waitForLocator(titleLocator);
//                titleLocator.first().click();
//
//                Locator addApproversLocator = page.locator(ADD_APPROVERS);
//                waitForLocator(addApproversLocator);
//
//                Locator projectManagerPopUpLocator = page.locator(PROJECT_MANAGER_POP_UP);
//                waitForLocator(projectManagerPopUpLocator);
//
//                if (i == 0 && addApproversLocator.isEnabled()) {
//                    addApproversLocator.click();
//                    if (projectManagerPopUpLocator.last().isEnabled() && projectManagerPopUpLocator.last().isVisible()) {
////TODO PR Approver Group B
//                        Locator projectManagerDropDownLocator = page.locator(PROJECT_MANAGER_DROP_DOWN);
//                        waitForLocator(projectManagerDropDownLocator);
//                        if (projectManagerDropDownLocator.isEnabled() && projectManagerDropDownLocator.isVisible()) {
//                            projectManagerDropDownLocator.click();
//
//                            String groupB = properties.getProperty("PRApproverGroupB");
//                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
//                            waitForLocator(searchFieldLocator);
//                            searchFieldLocator.fill(groupB);
//
//                            Locator groupBLocator = page.locator(getGroupB(groupB));
//                            waitForLocator(groupBLocator);
//                            String groupBId = groupBLocator.textContent();
//                            groupBLocator.first().click();
//                            groupIds.add(groupBId);
//                        }
////TODO PR Approver Group C
//                        Locator departmentManagerDropDown = page.locator(DEPARTMENT_MANAGER_DROP_DOWN);
//                        waitForLocator(departmentManagerDropDown);
//                        if (departmentManagerDropDown.isEnabled() && departmentManagerDropDown.isVisible()) {
//                            departmentManagerDropDown.click();
//
//                            String groupC = properties.getProperty("PRApproverGroupC");
//                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
//                            waitForLocator(searchFieldLocator);
//                            searchFieldLocator.fill(groupC);
//
//                            Locator groupCLocator = page.locator(getGroupC(groupC));
//                            waitForLocator(groupCLocator);
//                            String groupCId = groupCLocator.textContent();
//                            groupCLocator.first().click();
//                            groupIds.add(groupCId);
//                        }
////TODO PR Approver Group D
//                        Locator divisionManagerDropDown = page.locator(DIVISION_MANAGER);
//                        waitForLocator(divisionManagerDropDown);
//                        if (divisionManagerDropDown.isEnabled() && divisionManagerDropDown.isVisible()) {
//                            divisionManagerDropDown.click();
//
//                            String groupD = properties.getProperty("PRApproverGroupD");
//                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
//                            waitForLocator(searchFieldLocator);
//                            searchFieldLocator.fill(groupD);
//
//                            Locator groupDLocator = page.locator(getGroupD(groupD));
//                            waitForLocator(groupDLocator);
//                            String groupDId = groupDLocator.textContent();
//                            groupDLocator.first().click();
//                            groupIds.add(groupDId);
//                        }
//                        Locator saveUsersLocator = page.locator(SAVE_APPROVAL_USERS);
//                        waitForLocator(saveUsersLocator);
//                        saveUsersLocator.click();
//
//                        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
//                        waitForLocator(approveButtonLocator);
//                        approveButtonLocator.click();
//
//                        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
//                        waitForLocator(acceptButtonLocator);
//                        acceptButtonLocator.click();
//
//                        iLogout.performLogout();
//                    } else if (!projectManagerPopUpLocator.isVisible()) {
//                        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
//                        waitForLocator(approveButtonLocator);
//                        approveButtonLocator.click();
//
//                        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
//                        waitForLocator(acceptButtonLocator);
//                        acceptButtonLocator.click();
//
//                        iLogout.performLogout();
//                    }
//                } else {
//                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
//                    waitForLocator(approveButtonLocator);
//                    approveButtonLocator.click();
//
//                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
//                    waitForLocator(acceptButtonLocator);
//                    acceptButtonLocator.click();
//
//                    iLogout.performLogout();
//                }
//            }
//            int groupSize = groupIds.size() - 1;
//            if (approverMailId.startsWith("PR Approver Group")) {
//                for (int j = 0; j <= groupSize; j++) {
////                    iLogin.performLogin(groupIds.get(j), page);
//                    Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
//                    waitForLocator(myApprovalsButtonLocator);
//                    myApprovalsButtonLocator.click();
//
//                    String title = properties.getProperty("Title");
//                    Locator titleLocator = page.locator(getTitle(title));
//                    waitForLocator(titleLocator);
//                    titleLocator.first().click();
//
//                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
//                    waitForLocator(approveButtonLocator);
//                    approveButtonLocator.click();
//
//                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
//                    waitForLocator(acceptButtonLocator);
//                    acceptButtonLocator.click();
//
//                    iLogout.performLogout();
//                }
//                i += groupSize;
//            }
//        }
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//    }
