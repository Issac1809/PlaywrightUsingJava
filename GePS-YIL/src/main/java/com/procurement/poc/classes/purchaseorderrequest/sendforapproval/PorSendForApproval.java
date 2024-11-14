package com.procurement.poc.classes.purchaseorderrequest.sendforapproval;

import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.procurement.poc.interfaces.login.ILogin;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.purchaseorderrequests.IPorSendForApproval;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.procurement.poc.constants.purchaseorderrequests.LPorSendForApproval.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class PorSendForApproval implements IPorSendForApproval {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorSendForApproval() {
    }

//TODO Constructor
    public PorSendForApproval(ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

//    public List<String> getApprovers() {
//        List<String> matchingApprovers = null;
//        try {
//            String cormsquareMailId = "@cormsquare.com";
//            String sharklasersMailId = "@sharklasers.com";
//            String yokogawaId = "@yokogawa.com";
//            String userDesignation = "PR Approver Group";
//
//            String buyerMailId = properties.getProperty("Buyer");
//            iLogin.performLogin(buyerMailId);
//
//            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR.getLocator());
//            waitForLocator(porNavigationBarLocator);
//            porNavigationBarLocator.click();
//
//            String title = properties.getProperty("Title");
//            Locator titleLocator = page.locator(getString(title));
//            waitForLocator(titleLocator);
//            titleLocator.first().click();
//
//            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL__BUTTON.getLocator());
//            waitForLocator(sendForApprovalButtonLocator);
//            sendForApprovalButtonLocator.click();
//
//            Locator approvalPopupLocator = page.locator(APPROVAL_POP_UP.getLocator());
//            waitForLocator(approvalPopupLocator);
//
//            matchingApprovers = new ArrayList<>();
//            if (approvalPopupLocator.first().isEnabled() && approvalPopupLocator.first().isVisible() || !approvalPopupLocator.first().isHidden()) {
////TODO CFO
//                String cfoMailId = properties.getProperty("cfo");
//                Locator cfoDropdownLocator = page.locator(CFO_DROPDOWN_LOCATOR.getLocator());
//                waitForLocator(cfoDropdownLocator);
//                if (cfoDropdownLocator.isVisible()) {
//                    cfoDropdownLocator.click();
//                    Locator cfoIdLocator = page.locator(getString(cfoMailId));
//                    waitForLocator(cfoIdLocator);
//                    cfoIdLocator.click();
//                }
////TODO President/Director (Corporate)
//                String presidentMailId = properties.getProperty("PresidentDirectorCorporate");
//                Locator presidentDropdownLocator = page.locator(PRESIDENT_DROPDOWN_LOCATOR.getLocator());
//                waitForLocator(presidentDropdownLocator);
//                if (presidentDropdownLocator.isVisible()) {
//                    presidentDropdownLocator.click();
//                    Locator presidentIdLocator = page.locator(getString(presidentMailId));
//                    waitForLocator(presidentIdLocator);
//                    presidentIdLocator.click();
//                }
////TODO Submit
//                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON.getLocator());
//                waitForLocator(submitButtonLocator);
//                submitButtonLocator.click();
//
//                List<String> approversList = page.locator(APPROVERS_LIST.getLocator()).allTextContents();
//                approversList.removeIf(approvalId -> approvalId.contains("PR Approver Group A"));
//                for (String approver : approversList) {
//                    if (approver.endsWith(cormsquareMailId)) {
//                        matchingApprovers.add(approver);
//                    } else if (approver.startsWith(userDesignation) && !approver.contains("PR Approver Group A")) {
//                        matchingApprovers.add(approver);
//                    } else if (approver.endsWith(sharklasersMailId)) {
//                        matchingApprovers.add(approver);
//                    } else if (approver.endsWith(yokogawaId)) {
//                        matchingApprovers.add(approver);
//                    }
//                }
//                iLogout.performLogout();
//                return matchingApprovers;
//            } else {
//                List<String> approversList = page.locator(APPROVERS_LIST.getLocator()).allTextContents();
//                for (String approver : approversList) {
//                    if (approver.endsWith(cormsquareMailId)) {
//                        matchingApprovers.add(approver);
//                    } else if (approver.startsWith(userDesignation) && !approver.contains("PR Approver Group A")) {
//                        matchingApprovers.add(approver);
//                    } else if (approver.endsWith(sharklasersMailId)) {
//                        matchingApprovers.add(approver);
//                    } else if (approver.endsWith(yokogawaId)) {
//                        matchingApprovers.add(approver);
//                    }
//                }
//                iLogout.performLogout();
//                return matchingApprovers;
//            }
//        } catch (Exception error) {
//            System.out.println("What is the error: " + error.getMessage());
//        }
//        return matchingApprovers;
//    }

    public void sendForApproval() {
        try {
            String title = properties.getProperty("orderTitle");
            iLogin.performLogin(properties.getProperty("buyerEmail"));

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR.getLocator());
            waitForLocator(porNavigationBarLocator);
            porNavigationBarLocator.click();

            String getTitle = getString(title);
            Locator titleLocator = page.locator(getTitle);
            waitForLocator(titleLocator);
            titleLocator.first().click();

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL__BUTTON.getLocator());
            waitForLocator(sendForApprovalButtonLocator);
            sendForApprovalButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES.getLocator());
            waitForLocator(yesButtonLocator);
            Response response = page.waitForResponse(
                    resp -> resp.url().startsWith("https://dprocure-uat.cormsquare.com/api/Approvals") && resp.status() == 200,
                    yesButtonLocator::click
            );

            page.waitForLoadState(LoadState.NETWORKIDLE);

            List<String> matchingApprovers = new ArrayList<>();
            List<String> approvalTable = page.locator("#approvalData tbody tr td").allTextContents();
            String approverMailId = "@cormsquare.com";
            String approverMailId2 = "@sharklasers.com";
            String approverMailId3 = "@yokogawa.com";
            for (String approver : approvalTable) {
                if (approver.endsWith(approverMailId) || approver.endsWith(approverMailId2) || approver.endsWith(approverMailId3))
                {
                    matchingApprovers.add(approver);
                }
            }
            for (int i = 1; i <= matchingApprovers.size();i++){
                String approverEmail = matchingApprovers.get(i-1);
                if(i<matchingApprovers.size()) {
                    if (approverEmail.equals(matchingApprovers.get(i)))
                        matchingApprovers.remove(i);
                }
                PlaywrightFactory.saveToPropertiesFile(("Approver"+i),approverEmail);
                PlaywrightFactory.saveToPropertiesFile("ApproverCount", String.valueOf(matchingApprovers.size()));
            }

            String PORRefereneNumber = page.locator("#referenceId").textContent();
            PlaywrightFactory.saveToPropertiesFile("PORReferenceNumber",PORRefereneNumber);
            iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}