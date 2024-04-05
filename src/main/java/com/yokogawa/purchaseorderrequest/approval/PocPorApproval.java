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
import static com.yokogawa.variables.VariablesForCatalog.Title;
public class PocPorApproval implements PorApproval {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
    public void SendForApproval(String cfo, Page page) throws InterruptedException {
        page.locator("//*[contains(text(),'" + Title + "')]").first().click();
        page.locator("#btnNewSendApproval").click();
        Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").first();
        if (approvalPopup.isEnabled()) {
            //TODO CFO
            page.locator("#select2-role-7-container").click();
            page.locator("//li[contains(text(), '" + cfo + "')]").click();
//            //TODO President/Director (Corporate)
//            page.locator("#role-8").click();
//            page.locator("//*[contains(text(), '" + president + "')]").click();
            //TODO Submit
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
            Thread.sleep(3000);
        }
    }

    public List<String> GetPorApprovers(Page page) {
        List<String> matchingApprovers = new ArrayList<>();
        List<String> approvalTable = page.locator("#approvalData tbody tr td:nth-child(3)").allTextContents();
        approvalTable.removeIf(text -> text.contains("PR Approver Group A"));
        String approverMailId = "@cormsquare.com";
        String approverDesignation = "PR Approver Group";
        for (String approver : approvalTable) {
            if (approver.endsWith(approverMailId)) {
                matchingApprovers.add(approver);
            } else if (approver.startsWith(approverDesignation)) {
                matchingApprovers.add(approver);
            }
        }
        System.out.println(matchingApprovers);
        return matchingApprovers;
    }
    public void ApproverLogin(List<String> matchingApprovers, String PRApproverGroupB, String PRApproverGroupC, String PRApproverGroupD, Page page) {
        List<String> groupIds = new ArrayList<>();
        logout.Logout(page);

        for (int i = 0; i < matchingApprovers.size(); i++) {
            String approverMailId = matchingApprovers.get(i);
            System.out.println("ApproverMailId: " + approverMailId);
            if (approverMailId.endsWith("@cormsquare.com")) {
                login.Login(approverMailId, page);
                System.out.println("Approver : " + approverMailId + i);

//TODO Approver Approves POR
                page.locator("//span[contains(text(), 'Purchase Order Requests')]").click();
                page.locator("//span[contains(text(), '" + Title + "')]").first().click();
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
                    page.locator(".btn.btn-primary.bootbox-accept").click();
                    logout.Logout(page);
                } else if (addApprovers.isDisabled()) {
                    page.locator("#btnApprove").click();
                    page.locator(".btn.btn-primary.bootbox-accept").click();
                    logout.Logout(page);
                }
            }
            if (approverMailId.startsWith("PR Approver Group")) {
                if(approverMailId.contains("PR Approver Group A")){
                    continue;
                }
                int j = 0;
                while (j <= 2) {
                    login.Login(groupIds.get(j), page);
                    page.locator("//span[contains(text(), 'My Approvals')]").click();
                    page.locator("//span[contains(text(), '" + Title + "')]").first().click();
                    page.locator("#btnApprove").click();
                    page.locator(".btn.btn-primary.bootbox-accept").click();
                    logout.Logout(page);
                    j++;
                }
                    i += 3; //TODO For loop has index 0 to 7. But while loop has already completed index 1, 2, 3. So I'm hardcoding i+3.
            }
        }
    }
}
