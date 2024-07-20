package com.procurement.purchaseorderrequest.approval;
import com.interfaces.por.PorApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PocPorSendForApproval implements PorApproval {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocPorSendForApproval() {
    }

//TODO Constructor
    public PocPorSendForApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public List<String> SendForApproval() {
        List<String> matchingApprovers = null;
        try {
            String emailId = properties.getProperty("Buyer");
            loginPageInterface.LoginMethod(emailId);
            page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
            String title = properties.getProperty("Title");
            page.locator("//span[contains(text(), '" + title + "')]").first().click();
            page.locator("#btnNewSendApproval").click();
            Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").first();
            matchingApprovers = new ArrayList<>();
            if (approvalPopup.isEnabled() && approvalPopup.isVisible() || !approvalPopup.isHidden()) {
//TODO CFO
            String cfoId = properties.getProperty("cfo");
            Locator cfoDropdown = page.locator("#role-7");
            if (cfoDropdown.isVisible()){
                cfoDropdown.click();
                page.locator("//li[contains(text(), '" + cfoId + "')]").click();
            }
//TODO President/Director (Corporate)
                String presidentId = properties.getProperty("PresidentDirectorCorporate");
                Locator presidentDropdown = page.locator("#select2-role-8-container");
                if (presidentDropdown.isVisible()) {
                    presidentDropdown.click();
                    page.locator("//li[contains(text(), '" + presidentId + "')]").click();
                }
//TODO Submit
                page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
                List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
                approvalTable.removeIf(approvalId -> approvalId.contains("PR Approver Group A"));
                String approverMailId = "@cormsquare.com";
                String approverMailId2 = "@sharklasers.com";
                String approverMailId3 = "@yokogawa.com";
                String approverDesignation = "PR Approver Group";
                for (String approver : approvalTable) {
                    if (approver.endsWith(approverMailId)) {
                        matchingApprovers.add(approver);
                    }
                    else if (approver.startsWith(approverDesignation) && !approver.contains("PR Approver Group A")) {
                        matchingApprovers.add(approver);
                    }
                    else if (approver.endsWith(approverMailId2)) {
                        matchingApprovers.add(approver);
                    }
                    else if (approver.endsWith(approverMailId3)) {
                        matchingApprovers.add(approver);
                    }
                }
                logoutPageInterface.LogoutMethod();
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
                    else if (approver.startsWith(approverDesignation) && !approver.contains("PR Approver Group A")) {
                        matchingApprovers.add(approver);
                    }
                    else if (approver.endsWith(approverMailId2)) {
                        matchingApprovers.add(approver);
                    }
                    else if (approver.endsWith(approverMailId3)) {
                        matchingApprovers.add(approver);
                    }
                }
                logoutPageInterface.LogoutMethod();
                return matchingApprovers;
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
        return matchingApprovers;
    }
}