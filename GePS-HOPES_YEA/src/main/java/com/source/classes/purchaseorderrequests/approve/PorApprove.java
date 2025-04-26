package com.source.classes.purchaseorderrequests.approve;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.purchaseorderrequests.IPorApprove;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import static com.constants.purchaseorderrequests.LPorApprove.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorApprove implements IPorApprove {

    Logger logger;
    ObjectMapper objectMapper;
    PlaywrightFactory playwrightFactory;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IPorSendForApproval iPorSendForApproval;

    private PorApprove() {
    }

//TODO Constructor
    public PorApprove(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory, ObjectMapper objectMapper, IPorSendForApproval iPorSendForApproval) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
        this.objectMapper = objectMapper;
        this.iPorSendForApproval = iPorSendForApproval;
        this.logger = LoggerUtil.getLogger(PorApprove.class);
    }

    public void savePorAprovers(String type, String purchaseType) {
        List<String> porApprovers = new ArrayList<>();
        try {
            iPorSendForApproval.sendForApproval(type, purchaseType);

            String firstApprover = jsonNode.get("purchaseOrderRequests").get("approvers").asText();
            String appUrl = jsonNode.get("configSettings").get("appUrl").asText();
            int id = jsonNode.get("purchaseOrderRequests").get("purchaseOrderRequestId").asInt();

            iLogin.performLogin(firstApprover);

            Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
            myApprovalsButtonLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator addApproversLocator = page.locator(ADD_APPROVERS);

            Locator projectManagerPopUpLocator = page.locator(PROJECT_MANAGER_POP_UP);

            if (addApproversLocator.isEnabled()) {
                addApproversLocator.click();
                if (projectManagerPopUpLocator.last().isEnabled() && projectManagerPopUpLocator.last().isVisible()) {
//TODO PR Approver Group B
                    Locator projectManagerDropDownLocator = page.locator(PROJECT_MANAGER_DROP_DOWN);
                    if (projectManagerDropDownLocator.isEnabled() && projectManagerDropDownLocator.isVisible()) {
                        projectManagerDropDownLocator.click();

                        String groupB = jsonNode.get("mailIds").get("prApproverGroupBEmail").asText();
                        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                        searchFieldLocator.fill(groupB);

                        Locator groupBLocator = page.locator(getGroupB(groupB));
                        groupBLocator.first().click();
                    }
//TODO PR Approver Group C
                    Locator departmentManagerDropDown = page.locator(DEPARTMENT_MANAGER_DROP_DOWN);
                    if (departmentManagerDropDown.isEnabled() && departmentManagerDropDown.isVisible()) {
                        departmentManagerDropDown.click();

                        String groupC = jsonNode.get("mailIds").get("prApproverGroupCEmail").asText();
                        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                        searchFieldLocator.fill(groupC);

                        Locator groupCLocator = page.locator(getGroupC(groupC));
                        groupCLocator.first().click();
                    }
//TODO PR Approver Group D
                    Locator divisionManagerDropDown = page.locator(DIVISION_MANAGER);
                    if (divisionManagerDropDown.isEnabled() && divisionManagerDropDown.isVisible()) {
                        divisionManagerDropDown.click();

                        String groupD = jsonNode.get("mailIds").get("prApproverGroupDEmail").asText();
                        Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                        searchFieldLocator.fill(groupD);

                        Locator groupDLocator = page.locator(getGroupD(groupD));
                        groupDLocator.first().click();
                    }
                    Locator saveUsersLocator = page.locator(SAVE_APPROVAL_USERS);
                    saveUsersLocator.click();

                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                    approveButtonLocator.click();

                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                    acceptButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    APIResponse apiResponse = page.request().fetch(appUrl + "/api/Approvals?entityId=" + id + "&approvalTypeEnum=PurchaseOrderRequest");
                    JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
                    JsonNode approvers = jsonNode.get("approvers");

                    for (JsonNode approver : approvers) {
                        String porApprover = approver.get("email").asText();
                        porApprovers.add(porApprover);
                    }

                    playwrightFactory.savePorApproversIntoJsonFile("purchaseOrderRequests", "approvers", porApprovers);

                    iLogout.performLogout();
                } else {
                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                    approveButtonLocator.click();

                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                    acceptButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    APIResponse apiResponse = page.request().fetch(appUrl + "/api/Approvals?entityId=" + id + "&approvalTypeEnum=PurchaseOrderRequest");
                    JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
                    JsonNode approvers = jsonNode.get("approvers");

                    for (JsonNode approver : approvers) {
                        String porApprover = approver.get("email").asText();
                        porApprovers.add(porApprover);
                    }

                    playwrightFactory.savePorApproversIntoJsonFile("purchaseOrderRequests", "approvers", porApprovers);

                    iLogout.performLogout();
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in POR Approve function: {}", exception.getMessage());
        }
    }
}