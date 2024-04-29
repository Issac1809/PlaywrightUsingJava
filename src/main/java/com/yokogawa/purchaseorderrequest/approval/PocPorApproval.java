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
        page.waitForSelector("#btnNewSendApproval").click();
        Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").first();
        List<String> matchingApprovers = new ArrayList<>();
        if (approvalPopup.isEnabled() && approvalPopup.isVisible() || !approvalPopup.isHidden()) {
//TODO CFO
//            Locator cfoPopup = page.locator("#role-7");
//            if (cfoPopup.isVisible()){
//                cfoPopup.click();
//                page.waitForSelector("//li[contains(text(), '" + cfo + "')]").click();
//            }
//TODO President/Director (Corporate)
            Locator presidentPopup = page.locator("#select2-role-8-container");
            if (presidentPopup.isVisible()){
                presidentPopup.click();
                page.waitForSelector("//li[contains(text(), '" + president + "')]").click();
            }
//TODO Submit
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
            approvalTable.removeIf(text -> text.contains("PR Approver Group A"));
            String approverMailId = "@cormsquare.com";
            String approverMailId2 = "@sharklasers.com";
            String approverMailId3 = "@yokogawa.com";
            String approverDesignation = "PR Approver Group";
            for (String approver : approvalTable) {
                if (approver.endsWith(approverMailId)) {
                    matchingApprovers.add(approver);
                }
                if (approver.startsWith(approverDesignation) && !approver.contains("PR Approver Group A")) {
                    matchingApprovers.add(approver);
                }
                if (approver.endsWith(approverMailId2)) {
                    matchingApprovers.add(approver);
                }
                if (approver.endsWith(approverMailId3)) {
                    matchingApprovers.add(approver);
                }
            }
            logout.Logout(page);
            return matchingApprovers;
        } else {
            List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
            String approverMailId = "@cormsquare.com";
            String approverMailId2 = "@sharklasers.com";
            String approverMailId3 = "@yokogawa.com";
            String approverDesignation = "PR Approver Group";
            for (String approver : approvalTable) {
                if (approver.endsWith(approverMailId)) {
                    matchingApprovers.add(approver);
                }
                if (approver.startsWith(approverDesignation) && !approver.contains("PR Approver Group A")) {
                    matchingApprovers.add(approver);
                }
                if (approver.endsWith(approverMailId2)) {
                    matchingApprovers.add(approver);
                }
                if (approver.endsWith(approverMailId3)) {
                    matchingApprovers.add(approver);
                }
            }
            logout.Logout(page);
        }
        return matchingApprovers;
    }
    public void ApproverLogin(List<String> matchingApprovers, String PRApproverGroupB, String PRApproverGroupC, String PRApproverGroupD, Page page) throws InterruptedException {
        List<String> groupIds = new ArrayList<>();
        for (int i = 0; i < matchingApprovers.size(); i++) {
            String approverMailId = matchingApprovers.get(i);
            if (approverMailId.endsWith("@cormsquare.com") || approverMailId.endsWith("@sharklasers.com") || approverMailId.endsWith("@yokogawa.com")) {
                login.Login(approverMailId, page);
//TODO Approver Approves POR
                page.waitForSelector("//span[contains(text(), 'My Approvals')]").click();
                page.locator("//span[contains(text(), '" + NonCatalogTitle + "')]").first().click();
                Locator addApprovers = page.locator("#btnAddApprovers");
                Locator projectManagerPopUp = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").last();
                if (i == 0 && addApprovers.isEnabled()) {
                    addApprovers.click();
                    if (projectManagerPopUp.isEnabled() && projectManagerPopUp.isVisible()){
//TODO PR Approver Group B
                        Locator projectManagerDropDown = page.locator("#select2-PMBId-container");
                        if (projectManagerDropDown.isEnabled() && projectManagerDropDown.isVisible()){
                            projectManagerDropDown.click();
                            page.getByRole(AriaRole.SEARCHBOX).fill(PRApproverGroupB);
                            Locator getGroupB = page.locator("//li[contains(text(), '" + PRApproverGroupB + "')]");
                            String groupBId = getGroupB.textContent();
                            getGroupB.first().click();
                            groupIds.add(groupBId);
                        }
//TODO PR Approver Group C
                        Locator departmentManagerDropDown = page.locator("#select2-departmentManagerId-container");
                        if (departmentManagerDropDown.isEnabled() && departmentManagerDropDown.isVisible()) {
                            departmentManagerDropDown.click();
                            page.getByRole(AriaRole.SEARCHBOX).fill(PRApproverGroupC);
                            Locator getGroupC = page.locator("//li[contains(text(), '" + PRApproverGroupC + "')]");
                            String groupCId = getGroupC.textContent();
                            getGroupC.first().click();
                            groupIds.add(groupCId);
                        }
//TODO PR Approver Group D
                        Locator divisionManagerDropDown = page.locator("#select2-divisionManagerId-container");
                        if (divisionManagerDropDown.isEnabled() && divisionManagerDropDown.isVisible()) {
                            divisionManagerDropDown.click();
                            page.getByRole(AriaRole.SEARCHBOX).fill(PRApproverGroupD);
                            Locator getGroupD = page.locator("//li[contains(text(), '" + PRApproverGroupD + "')]");
                            String groupDId = getGroupD.textContent();
                            getGroupD.first().click();
                            groupIds.add(groupDId);
                        }
                        page.locator("#btnSendUserFromPM").click();
                        page.locator("#btnApprove").click();
                        page.locator(".bootbox-accept").click();
                        logout.Logout(page);
                    } else if (!projectManagerPopUp.isVisible()) {
                    page.locator("#btnApprove").click();
                    page.locator(".bootbox-accept").click();
                    logout.Logout(page);
                    }
                } else {
                    page.locator("#btnApprove").click();
                    page.locator(".bootbox-accept").click();
                    logout.Logout(page);
                }
            }
            int size = groupIds.size() - 1;
            if (approverMailId.startsWith("PR Approver Group")) {
                for(int j = 0; j <= size; j++){
                    login.Login(groupIds.get(j), page);
                    page.waitForSelector("//span[contains(text(), 'My Approvals')]").click();
                    page.locator("//span[contains(text(), '" + NonCatalogTitle + "')]").first().click();
                    page.waitForSelector("#btnApprove").click();
                    page.waitForSelector(".bootbox-accept").click();
                    logout.Logout(page);
                }
                i += size;
            }
        }
    }
}