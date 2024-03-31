package org.purchaseorderrequest.approval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.variables.VariablesForCatalog;

import java.util.ArrayList;
import java.util.List;

public class PocPorApproval implements PorApproval {
    Page page;
    public void SendForApproval(String cfo, String president, Page page) {
        page.locator("#btnNewSendApproval").click();
        Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]");
        if (approvalPopup.isEnabled()) {
            //TODO CFO
            page.locator("#select2-role-7-container").click();
            page.locator("//li[contains(text(), '" + cfo + "')]").click();
            //TODO President/Director (Corporate)
            page.locator("#role-8").click();
            page.locator("//*[contains(text(), '" + president + "')]").click();
            //TODO Submit
            page.locator("//button[contains(text(), 'Submit')]").click();
        }
    }
    public List<String> GetPorApprovers(Page page) {
        List<String> approvalTable = page.locator("#approvalData tbody tr").allInnerTexts();
        String approverMailId = "@cormsquare.com";
        List<String> matchingApprovers = new ArrayList<>();
        for (String approver : approvalTable) {
            if (approver.endsWith(approverMailId)) {
                matchingApprovers.add(approver);
            }
        }
        return matchingApprovers;
    }
    public void ApproverLogin(List<String> matchingApprovers, String projectManager, String departmentManager, String businessUnitManager) {
//TODO Logout Avatar Button
        page.locator("//header/div[1]/div[2]/ul[1]/li[3]/div[1]/a[1]/div[1]/img[1]").click();
        page.locator("//a[@onclick='user_logout()']").click();

        for (int i = 0; i <= matchingApprovers.size(); i++) {
            String approverMailId = matchingApprovers.get(i);
//TODO Login Page
            page.locator("Input_Email").fill(approverMailId);
            page.locator("Input_Password").fill(VariablesForCatalog.Password);
            page.locator("login-submit").click();

//TODO Approver Approves POR
            page.locator("//span[contains(text(), 'Purchase Order Requests')]").click();
            page.locator("//span[contains(text(), '"+ VariablesForCatalog.Title +"')]").click();
            Locator addApprovers = page.locator("#btnAddApprovers");
            if(addApprovers.isEnabled()) {
                addApprovers.click();
                //TODO PR Approver Group B
                page.locator("#select2-PMBId-container").click();
                page.getByRole(AriaRole.SEARCHBOX).fill(projectManager);
                page.locator("//li[contains(text(), '" + projectManager + "')]").click();
                //TODO PR Approver Group C
                page.locator("#select2-PMBId-container").click();
                page.getByRole(AriaRole.SEARCHBOX).fill(departmentManager);
                page.locator("//li[contains(text(), '" + departmentManager + "')]").click();
                //TODO PR Approver Group D
                page.locator("#select2-PMBId-container").click();
                page.getByRole(AriaRole.SEARCHBOX).fill(businessUnitManager);
                page.locator("//li[contains(text(), '" + businessUnitManager + "')]").click();
            }

                page.locator("//li[contains(text(), 'Submit')]").click();
                page.locator("#btnApprove").click();
                page.locator("//button[contains(text(), 'Submit')]").click();
        }
//TODO Logout Avatar Button
            page.locator("//header/div[1]/div[2]/ul[1]/li[3]/div[1]/a[1]/div[1]/img[1]").click();
            page.locator("//a[@onclick='user_logout()']").click();
    }
}
