package com.poc.classes.purchaseorderrequest.sendforapproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.purchaseorderrequests.IPorSendForApproval;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.constants.purchaseorderrequests.LPorSendForApproval.*;
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

    public List<String> getApprovers() {
        List<String> matchingApprovers = null;
        try {
            String cormsquareMailId = "@cormsquare.com";
            String sharklasersMailId = "@sharklasers.com";
            String yokogawaId = "@yokogawa.com";
            String userDesignation = "PR Approver Group";

            String buyerMailId = properties.getProperty("Buyer");
            iLogin.performLogin(buyerMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            waitForLocator(porNavigationBarLocator);
            porNavigationBarLocator.click();

            String title = properties.getProperty("Title");
            Locator titleLocator = page.locator(getTitle(title));
            waitForLocator(titleLocator);
            titleLocator.first().click();

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL__BUTTON);
            waitForLocator(sendForApprovalButtonLocator);
            sendForApprovalButtonLocator.click();

            Locator approvalPopupLocator = page.locator(APPROVAL_POP_UP);
            waitForLocator(approvalPopupLocator);

            matchingApprovers = new ArrayList<>();
            if (approvalPopupLocator.first().isEnabled() && approvalPopupLocator.first().isVisible() || !approvalPopupLocator.first().isHidden()) {
//TODO CFO
                String cfoMailId = properties.getProperty("cfo");
                Locator cfoDropdownLocator = page.locator(CFO_DROPDOWN_LOCATOR);
                waitForLocator(cfoDropdownLocator);
                if (cfoDropdownLocator.isVisible()) {
                    cfoDropdownLocator.click();
                    Locator cfoIdLocator = page.locator(getCfoId(cfoMailId));
                    waitForLocator(cfoIdLocator);
                    cfoIdLocator.click();
                }
//TODO President/Director (Corporate)
                String presidentMailId = properties.getProperty("PresidentDirectorCorporate");
                Locator presidentDropdownLocator = page.locator(PRESIDENT_DROPDOWN_LOCATOR);
                waitForLocator(presidentDropdownLocator);
                if (presidentDropdownLocator.isVisible()) {
                    presidentDropdownLocator.click();
                    Locator presidentIdLocator = page.locator(getPresidentId(presidentMailId));
                    waitForLocator(presidentIdLocator);
                    presidentIdLocator.click();
                }
//TODO Submit
                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
                waitForLocator(submitButtonLocator);
                submitButtonLocator.click();

                List<String> approversList = page.locator(APPROVERS_LIST).allTextContents();
                approversList.removeIf(approvalId -> approvalId.contains("PR Approver Group A"));
                for (String approver : approversList) {
                    if (approver.endsWith(cormsquareMailId)) {
                        matchingApprovers.add(approver);
                    } else if (approver.startsWith(userDesignation) && !approver.contains("PR Approver Group A")) {
                        matchingApprovers.add(approver);
                    } else if (approver.endsWith(sharklasersMailId)) {
                        matchingApprovers.add(approver);
                    } else if (approver.endsWith(yokogawaId)) {
                        matchingApprovers.add(approver);
                    }
                }
                iLogout.performLogout();
                return matchingApprovers;
            } else {
                List<String> approversList = page.locator(APPROVERS_LIST).allTextContents();
                for (String approver : approversList) {
                    if (approver.endsWith(cormsquareMailId)) {
                        matchingApprovers.add(approver);
                    } else if (approver.startsWith(userDesignation) && !approver.contains("PR Approver Group A")) {
                        matchingApprovers.add(approver);
                    } else if (approver.endsWith(sharklasersMailId)) {
                        matchingApprovers.add(approver);
                    } else if (approver.endsWith(yokogawaId)) {
                        matchingApprovers.add(approver);
                    }
                }
                iLogout.performLogout();
                return matchingApprovers;
            }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
        return matchingApprovers;
    }
}