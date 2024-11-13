package com.poc.classes.purchaseorderrequest.approve;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.purchaseorderrequests.IPorApprove;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.constants.purchaseorderrequests.LPorApprove.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorApprove implements IPorApprove {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorApprove() {
    }

//TODO Constructor
    public PorApprove(ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void approverLogin(List<String> approvers) {
        try {
        String cormsquareMailId = "@cormsquare.com";
        String sharklasersMailId = "@sharklasers.com";
        String yokogawaMailId = "@yokogawa.com";
        List<String> groupIds = new ArrayList<>();
        for (int i = 0; i < approvers.size(); i++) {
            String approverMailId = approvers.get(i);
            if (approverMailId.endsWith(cormsquareMailId) || approverMailId.endsWith(sharklasersMailId) || approverMailId.endsWith(yokogawaMailId)) {
                iLogin.performLogin(approverMailId, page);
//TODO Approver Approves POR
                Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
                waitForLocator(myApprovalsButtonLocator);
                myApprovalsButtonLocator.click();

                String title = properties.getProperty("Title");
                Locator titleLocator = page.locator(getTitle(title));
                waitForLocator(titleLocator);
                titleLocator.first().click();

                Locator addApproversLocator = page.locator(ADD_APPROVERS);
                waitForLocator(addApproversLocator);

                Locator projectManagerPopUpLocator = page.locator(PROJECT_MANAGER_POP_UP);
                waitForLocator(projectManagerPopUpLocator);

                if (i == 0 && addApproversLocator.isEnabled()) {
                    addApproversLocator.click();
                    if (projectManagerPopUpLocator.last().isEnabled() && projectManagerPopUpLocator.last().isVisible()) {
//TODO PR Approver Group B
                        Locator projectManagerDropDownLocator = page.locator(PROJECT_MANAGER_DROP_DOWN);
                        waitForLocator(projectManagerDropDownLocator);
                        if (projectManagerDropDownLocator.isEnabled() && projectManagerDropDownLocator.isVisible()) {
                            projectManagerDropDownLocator.click();

                            String groupB = properties.getProperty("PRApproverGroupB");
                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                            waitForLocator(searchFieldLocator);
                            searchFieldLocator.fill(groupB);

                            Locator groupBLocator = page.locator(getGroupB(groupB));
                            waitForLocator(groupBLocator);
                            String groupBId = groupBLocator.textContent();
                            groupBLocator.first().click();
                            groupIds.add(groupBId);
                        }
//TODO PR Approver Group C
                        Locator departmentManagerDropDown = page.locator(DEPARTMENT_MANAGER_DROP_DOWN);
                        waitForLocator(departmentManagerDropDown);
                        if (departmentManagerDropDown.isEnabled() && departmentManagerDropDown.isVisible()) {
                            departmentManagerDropDown.click();

                            String groupC = properties.getProperty("PRApproverGroupC");
                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                            waitForLocator(searchFieldLocator);
                            searchFieldLocator.fill(groupC);

                            Locator groupCLocator = page.locator(getGroupC(groupC));
                            waitForLocator(groupCLocator);
                            String groupCId = groupCLocator.textContent();
                            groupCLocator.first().click();
                            groupIds.add(groupCId);
                        }
//TODO PR Approver Group D
                        Locator divisionManagerDropDown = page.locator(DIVISION_MANAGER);
                        waitForLocator(divisionManagerDropDown);
                        if (divisionManagerDropDown.isEnabled() && divisionManagerDropDown.isVisible()) {
                            divisionManagerDropDown.click();

                            String groupD = properties.getProperty("PRApproverGroupD");
                            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
                            waitForLocator(searchFieldLocator);
                            searchFieldLocator.fill(groupD);

                            Locator groupDLocator = page.locator(getGroupD(groupD));
                            waitForLocator(groupDLocator);
                            String groupDId = groupDLocator.textContent();
                            groupDLocator.first().click();
                            groupIds.add(groupDId);
                        }
                        Locator saveUsersLocator = page.locator(SAVE_APPROVAL_USERS);
                        waitForLocator(saveUsersLocator);
                        saveUsersLocator.click();

                        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                        waitForLocator(approveButtonLocator);
                        approveButtonLocator.click();

                        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                        waitForLocator(acceptButtonLocator);
                        acceptButtonLocator.click();

                        iLogout.performLogout();
                    } else if (!projectManagerPopUpLocator.isVisible()) {
                        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                        waitForLocator(approveButtonLocator);
                        approveButtonLocator.click();

                        Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                        waitForLocator(acceptButtonLocator);
                        acceptButtonLocator.click();

                        iLogout.performLogout();
                    }
                } else {
                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                    waitForLocator(approveButtonLocator);
                    approveButtonLocator.click();

                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                    waitForLocator(acceptButtonLocator);
                    acceptButtonLocator.click();

                    iLogout.performLogout();
                }
            }
            int groupSize = groupIds.size() - 1;
            if (approverMailId.startsWith("PR Approver Group")) {
                for (int j = 0; j <= groupSize; j++) {
                    iLogin.performLogin(groupIds.get(j), page);
                    Locator myApprovalsButtonLocator = page.locator(MY_APPROVALS);
                    waitForLocator(myApprovalsButtonLocator);
                    myApprovalsButtonLocator.click();

                    String title = properties.getProperty("Title");
                    Locator titleLocator = page.locator(getTitle(title));
                    waitForLocator(titleLocator);
                    titleLocator.first().click();

                    Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
                    waitForLocator(approveButtonLocator);
                    approveButtonLocator.click();

                    Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
                    waitForLocator(acceptButtonLocator);
                    acceptButtonLocator.click();

                    iLogout.performLogout();
                }
                i += groupSize;
            }
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}