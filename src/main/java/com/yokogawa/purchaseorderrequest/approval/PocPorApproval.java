package com.yokogawa.purchaseorderrequest.approval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import java.util.ArrayList;
import java.util.List;
import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;

public class PocPorApproval implements PorApproval {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public List<String> SendForApproval(String cfo, String president, Page page) {
        page.locator("//*[contains(text(),'" + NonCatalogTitle + "')]").first().click();
        page.locator("#btnNewSendApproval").click();
        Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").first();
        List<String> matchingApprovers = new ArrayList<>();
        if (approvalPopup.isEnabled()) {
//TODO CFO
//            page.locator("#role-7").click();
//            page.locator("//li[contains(text(), '" + cfo + "')]").click();
//TODO President/Director (Corporate)
            page.locator("#select2-role-8-container").click();
            page.locator("//li[contains(text(), '" + president + "')]").click();
//TODO Submit
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            page.pause();
            List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
            approvalTable.removeIf(text -> text.contains("PR Approver Group A"));
            String approverMailId = "@cormsquare.com";
            String approverMailId2 = "@sharklasers.com";
            String approverMailId3 = "@yokogawa.com";
            String approverDesignation = "PR Approver Group";
            for (String approver : approvalTable) {
                if (approver.endsWith(approverMailId)) {
                    matchingApprovers.add(approver);
                } if (approver.startsWith(approverDesignation) && !approver.contains("PR Approver Group A")) {
                    matchingApprovers.add(approver);
                } if (approver.endsWith(approverMailId2)) {
                    matchingApprovers.add(approver);
                } if (approver.endsWith(approverMailId3)) {
                    matchingApprovers.add(approver);
                }
            }
            logout.Logout(page);
            return matchingApprovers;
        } else {
            List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
            String approverMailId = "@cormsquare.com";
            for (String approver : approvalTable) {
                if (approver.endsWith(approverMailId)) {
                    matchingApprovers.add(approver);
                }
            }
            logout.Logout(page);
        }
        return matchingApprovers;
    }
    public void ApproverLogin(List<String> matchingApprovers, String PRApproverGroupB, String PRApproverGroupC, String PRApproverGroupD, Page page) {
        List<String> groupIds = new ArrayList<>();
        for (int i = 0; i < matchingApprovers.size(); i++) {
            String approverMailId = matchingApprovers.get(i);
            if (approverMailId.endsWith("@cormsquare.com") || approverMailId.endsWith("@sharklasers.com") || approverMailId.endsWith("@yokogawa.com")) {
                login.Login(approverMailId, page);

//TODO Approver Approves POR
                page.locator("//span[contains(text(), 'My Approvals')]").click();
                page.locator("//span[contains(text(), '" + NonCatalogTitle + "')]").first().click();
                Locator addApprovers = page.locator("#btnAddApprovers");

                if (i == 0 && addApprovers.isEnabled()) {
                    addApprovers.click();

                    //TODO PR Approver Group B
                    page.locator("#select2-PMBId-container").click();
                    page.getByRole(AriaRole.SEARCHBOX).fill(PRApproverGroupB);
                    Locator getGroupB = page.locator("//li[contains(text(), '" + PRApproverGroupB + "')]");
                    String groupBId = getGroupB.textContent();
                    getGroupB.first().click();
                    groupIds.add(groupBId);

                    //TODO PR Approver Group C
                    page.locator("#select2-departmentManagerId-container").click();
                    page.getByRole(AriaRole.SEARCHBOX).fill(PRApproverGroupC);
                    Locator getGroupC = page.locator("//li[contains(text(), '" + PRApproverGroupC + "')]");
                    String groupCId = getGroupC.textContent();
                    getGroupC.first().click();
                    groupIds.add(groupCId);

                    //TODO PR Approver Group D
                    page.locator("#select2-divisionManagerId-container").click();
                    page.getByRole(AriaRole.SEARCHBOX).fill(PRApproverGroupD);
                    Locator getGroupD = page.locator("//li[contains(text(), '" + PRApproverGroupD + "')]");
                    String groupDId = getGroupD.textContent();
                    getGroupD.first().click();
                    groupIds.add(groupDId);

                    page.locator("#btnSendUserFromPM").click();
                    page.locator("#btnApprove").click();
                    page.locator(".bootbox-accept").click();
                    logout.Logout(page);
                } else if (addApprovers.isDisabled() || !addApprovers.isVisible() || addApprovers.isHidden()) {
                    page.locator("#btnApprove").click();
                    page.locator(".bootbox-accept").click();
                    logout.Logout(page);
                }
            }
            if (approverMailId.startsWith("PR Approver Group")) {
                int j = 0;
                while (j <= 2) {
                    login.Login(groupIds.get(j), page);
                    page.locator("//span[contains(text(), 'My Approvals')]").click();
                    page.locator("//span[contains(text(), '" + NonCatalogTitle + "')]").first().click();
                    page.locator("#btnApprove").click();
                    page.locator(".bootbox-accept").click();
                    logout.Logout(page);
                    j++;
                }
                    i += 2; //TODO For loop has index 0 to 7. But while loop has already completed index 1, 2, 3. So I'm hardcoding i+2.
            }
        }
    }
}
