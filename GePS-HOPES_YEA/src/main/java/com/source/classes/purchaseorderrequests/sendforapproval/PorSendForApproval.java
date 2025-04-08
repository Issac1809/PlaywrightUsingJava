package com.source.classes.purchaseorderrequests.sendforapproval;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import static com.constants.purchaseorderrequests.LPorSendForApproval.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorSendForApproval implements IPorSendForApproval {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private PorSendForApproval() {
    }

//TODO Constructor
    public PorSendForApproval(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout) {
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(PorSendForApproval.class);
    }

    public List<String> getApprovers(String type, String purchaseType) {
        List<String> matchingApprovers = null;
        try {
            String cormsquareMailId = "@cormsquare.com";
            String sharklasersMailId = "@sharklasers.com";
            String yokogawaId = "@yokogawa.com";
            String userDesignation = "PR Approver Group";

            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            porNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL__BUTTON);
            sendForApprovalButtonLocator.click();

            Locator approvalPopupLocator = page.locator(APPROVAL_POP_UP);

            matchingApprovers = new ArrayList<>();
            if(approvalPopupLocator.first().isEnabled() && approvalPopupLocator.first().isVisible() || !approvalPopupLocator.first().isHidden()) {
//TODO CFO
                String cfoMailId = jsonNode.get("mailIds").get("cfoEmail").asText();
                Locator cfoDropdownLocator = page.locator(CFO_DROPDOWN_LOCATOR);
                if (cfoDropdownLocator.isVisible()) {
                    cfoDropdownLocator.click();
                    Locator cfoIdLocator = page.locator(getCfoId(cfoMailId));
                    cfoIdLocator.click();
                }
//TODO President/Director (Corporate)
                String presidentMailId = jsonNode.get("mailIds").get("presidentDirectorCorporateEmail").asText();
                Locator presidentDropdownLocator = page.locator(PRESIDENT_DROPDOWN_LOCATOR);
                if (presidentDropdownLocator.isVisible()) {
                    presidentDropdownLocator.click();
                    Locator presidentIdLocator = page.locator(getPresidentId(presidentMailId));
                    presidentIdLocator.click();
                }
//TODO Submit
                Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
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
        } catch (Exception exception) {
            logger.error("Exception in POR Send For Approval function: {}", exception.getMessage());
        }
        return matchingApprovers;
    }
}