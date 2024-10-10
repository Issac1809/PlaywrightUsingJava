package com.procurement.purchaseorderrequest.approval;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
import com.interfaces.por.PorApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PocPorApproval implements PorApproval {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private PocPorApproval() {
    }

//TODO Constructor
    public PocPorApproval(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public List<String> SendForApproval() {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.locator("//*[contains(text(), 'Purchase Order Requests')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#btnNewSendApproval").click();
        Locator approvalPopup = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").first();
        List<String> matchingApprovers = new ArrayList<>();
        if (approvalPopup.isEnabled() && approvalPopup.isVisible() || !approvalPopup.isHidden()) {
//TODO CFO
//            Locator cfoPopup = page.locator("#role-7");
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
        return matchingApprovers;
    }

    public void ApproverLogin(List<String> matchingApprovers) {
        List<String> groupIds = new ArrayList<>();
        for (int i = 0; i < matchingApprovers.size(); i++) {
            String approverMailId = matchingApprovers.get(i);
            if (approverMailId.endsWith("@cormsquare.com") || approverMailId.endsWith("@sharklasers.com") || approverMailId.endsWith("@yokogawa.com")) {
                loginPageInterface.LoginMethod(approverMailId, page);
//TODO Approver Approves POR
                page.waitForSelector("//span[contains(text(), 'My Approvals')]").click();
                String title = properties.getProperty("Title");
                page.locator("//span[contains(text(), '" + title + "')]").first().click();
                Locator addApprovers = page.locator("#btnAddApprovers");
                Locator projectManagerPopUp = page.locator("//h3[contains(text(), 'Purchase Order Request Send For Approval')]").last();
                if (i == 0 && addApprovers.isEnabled()) {
                    addApprovers.click();
                    if (projectManagerPopUp.isEnabled() && projectManagerPopUp.isVisible()) {
//TODO PR Approver Group B
                        Locator projectManagerDropDown = page.locator("#select2-PMBId-container");
                        if (projectManagerDropDown.isEnabled() && projectManagerDropDown.isVisible()) {
                            projectManagerDropDown.click();
                            String prApproverGroupB = properties.getProperty("PRApproverGroupB");
                            page.getByRole(AriaRole.SEARCHBOX).fill(prApproverGroupB);
                            Locator getGroupB = page.locator("//li[contains(text(), '" + prApproverGroupB + "')]");
                            String groupBId = getGroupB.textContent();
                            getGroupB.first().click();
                            groupIds.add(groupBId);
                        }
//TODO PR Approver Group C
                        Locator departmentManagerDropDown = page.locator("#select2-departmentManagerId-container");
                        if (departmentManagerDropDown.isEnabled() && departmentManagerDropDown.isVisible()) {
                            departmentManagerDropDown.click();
                            String prApproverGroupC = properties.getProperty("PRApproverGroupC");
                            page.getByRole(AriaRole.SEARCHBOX).fill(prApproverGroupC);
                            Locator getGroupC = page.locator("//li[contains(text(), '" + prApproverGroupC + "')]");
                            String groupCId = getGroupC.textContent();
                            getGroupC.first().click();
                            groupIds.add(groupCId);
                        }
//TODO PR Approver Group D
                        Locator divisionManagerDropDown = page.locator("#select2-divisionManagerId-container");
                        if (divisionManagerDropDown.isEnabled() && divisionManagerDropDown.isVisible()) {
                            divisionManagerDropDown.click();
                            String prApproverGroupD = properties.getProperty("PRApproverGroupD");
                            page.getByRole(AriaRole.SEARCHBOX).fill(prApproverGroupD);
                            Locator getGroupD = page.locator("//li[contains(text(), '" + prApproverGroupD + "')]");
                            String groupDId = getGroupD.textContent();
                            getGroupD.first().click();
                            groupIds.add(groupDId);
                        }
                        page.locator("#btnSendUserFromPM").click();
                        page.locator("#btnApprove").click();
                        page.locator(".bootbox-accept").click();
                        logoutPageInterface.LogoutMethod();
                    } else if (!projectManagerPopUp.isVisible()) {
                        page.locator("#btnApprove").click();
                        page.locator(".bootbox-accept").click();
                        logoutPageInterface.LogoutMethod();
                    }
                } else {
                    page.locator("#btnApprove").click();
                    page.locator(".bootbox-accept").click();
                    logoutPageInterface.LogoutMethod();
                }
            }
            int size = groupIds.size() - 1;
            if (approverMailId.startsWith("PR Approver Group")) {
                for (int j = 0; j <= size; j++) {
                    loginPageInterface.LoginMethod(groupIds.get(j), page);
                    page.waitForSelector("//span[contains(text(), 'My Approvals')]").click();
                    String title = properties.getProperty("Title");
                    page.locator("//span[contains(text(), '" + title + "')]").first().click();
                    page.waitForSelector("#btnApprove").click();
                    page.waitForSelector(".bootbox-accept").click();
                    logoutPageInterface.LogoutMethod();
                }
                i += size;
            }
        }
    }
}