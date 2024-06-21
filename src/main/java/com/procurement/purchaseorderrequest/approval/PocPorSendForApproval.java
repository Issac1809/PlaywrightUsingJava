package com.procurement.purchaseorderrequest.approval;
import com.interfaces.PorApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
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
            loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
            page.waitForSelector("//*[contains(text(), 'Purchase Order Requests')]").click();
            String title = properties.getProperty("Title");
            page.locator("//span[contains(text(), '" + title + "')]").first().click();
            page.waitForSelector("#btnNewSendApproval").click();
            Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").first();
            matchingApprovers = new ArrayList<>();
            if (approvalPopup.isEnabled() && approvalPopup.isVisible() || !approvalPopup.isHidden()) {
//TODO CFO
//            Locator cfoPopup = page.waitForSelector("#role-7");
//            if (cfoPopup.isVisible()){
//                cfoPopup.click();
//                page.waitForSelector("//li[contains(text(), '" + properties.getProperty("cfo") + "')]").click();
//            }
//TODO President/Director (Corporate)
                Locator presidentPopup = page.locator("#select2-role-8-container");
                if (presidentPopup.isVisible()) {
                    presidentPopup.click();
                    page.waitForSelector("//li[contains(text(), '" + properties.getProperty("PresidentDirectorCorporate") + "')]").click();
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
                logoutPageInterface.LogoutMethod();
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
        return matchingApprovers;
    }
}