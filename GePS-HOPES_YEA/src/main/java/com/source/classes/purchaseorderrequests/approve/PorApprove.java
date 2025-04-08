package com.source.classes.purchaseorderrequests.approve;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.purchaseorderrequests.IPorApprove;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import static com.constants.purchaseorderrequests.LPorApprove.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorApprove implements IPorApprove {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorApprove() {
    }

//TODO Constructor
    public PorApprove(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(PorApprove.class);
    }

    public void approverLogin(String type, String purchaseType, List<String> approvers) {
        try {
            String cormsquareMailId = "@cormsquare.com";
            String sharklasersMailId = "@sharklasers.com";
            String yokogawaMailId = "@yokogawa.com";

            List<String> groupIds = new ArrayList<>();
            for (int i = 0; i < approvers.size(); i++) {
                String approverMailId = approvers.get(i);
                if (approverMailId.endsWith(cormsquareMailId) || approverMailId.endsWith(sharklasersMailId) || approverMailId.endsWith(yokogawaMailId)) {
                iLogin.performLogin(approverMailId);
//TODO Approver Approves POR
                Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
                myApprovalsButtonLocator.click();

                String title = getTransactionTitle(type, purchaseType);
                Locator titleLocator = page.locator(getTitle(title));
                titleLocator.first().click();

                Locator addApproversLocator = page.locator(ADD_APPROVERS);

                Locator projectManagerPopUpLocator = page.locator(PROJECT_MANAGER_POP_UP);

                if (i == 0 && addApproversLocator.isEnabled()) {
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
                            String groupBId = groupBLocator.textContent();
                            groupBLocator.first().click();
                            groupIds.add(groupBId);
                        }
//TODO PR Approver Group C
                        Locator departmentManagerDropDown = page.locator(DEPARTMENT_MANAGER_DROP_DOWN);
                        if (departmentManagerDropDown.isEnabled() && departmentManagerDropDown.isVisible()) {
                            departmentManagerDropDown.click();

                            String groupC = jsonNode.get("mailIds").get("prApproverGroupCEmail").asText();
                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                            searchFieldLocator.fill(groupC);

                            Locator groupCLocator = page.locator(getGroupC(groupC));
                            String groupCId = groupCLocator.textContent();
                            groupCLocator.first().click();
                            groupIds.add(groupCId);
                        }
//TODO PR Approver Group D
                        Locator divisionManagerDropDown = page.locator(DIVISION_MANAGER);
                        if (divisionManagerDropDown.isEnabled() && divisionManagerDropDown.isVisible()) {
                            divisionManagerDropDown.click();

                            String groupD = jsonNode.get("mailIds").get("prApproverGroupDEmail").asText();
                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                            searchFieldLocator.fill(groupD);

                            Locator groupDLocator = page.locator(getGroupD(groupD));
                            String groupDId = groupDLocator.textContent();
                            groupDLocator.first().click();
                            groupIds.add(groupDId);
                        }
                        Locator saveUsersLocator = page.locator(SAVE_APPROVAL_USERS);
                        saveUsersLocator.click();

                        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                        approveButtonLocator.click();

                        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                        acceptButtonLocator.click();

                        iLogout.performLogout();
                    } else if (!projectManagerPopUpLocator.isVisible()) {
                        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                        approveButtonLocator.click();

                        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                        acceptButtonLocator.click();

                        iLogout.performLogout();
                    }
                } else {
                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                    approveButtonLocator.click();

                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                    acceptButtonLocator.click();

                    iLogout.performLogout();
                }
            }
            int groupSize = groupIds.size() - 1;
            if (approverMailId.startsWith("PR Approver Group")) {
                for (int j = 0; j <= groupSize; j++) {
                    iLogin.performLogin(groupIds.get(j));
                    Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
                    myApprovalsButtonLocator.click();

                    String title = getTransactionTitle(type, purchaseType);
                    Locator titleLocator = page.locator(getTitle(title));
                    titleLocator.first().click();

                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                    approveButtonLocator.click();

                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                    acceptButtonLocator.click();

                    iLogout.performLogout();
                }
                i += groupSize;
            }
        }
        } catch (Exception exception) {
            logger.error("Exception in POR Approver Login function: {}", exception.getMessage());
        }
    }
}