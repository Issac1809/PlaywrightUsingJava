package com.procurement.purchaseorderrequest.approval;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import com.interfaces.PorApprove;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Approve implements PorApprove {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private Approve() {
    }

    //TODO Constructor
    public Approve(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface) {
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void ApproverLogin(List<String> matchingApprovers) {
        try {
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
                        page.waitForSelector("#btnSendUserFromPM").click();
                        page.waitForSelector("#btnApprove").click();
                        page.waitForSelector(".bootbox-accept").click();
                        logoutPageInterface.LogoutMethod();
                    } else if (!projectManagerPopUp.isVisible()) {
                        page.waitForSelector("#btnApprove").click();
                        page.waitForSelector(".bootbox-accept").click();
                        logoutPageInterface.LogoutMethod();
                    }
                } else {
                    page.waitForSelector("#btnApprove").click();
                    page.waitForSelector(".bootbox-accept").click();
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
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}