package com.procurement.nonPoc.classes.requisition.sendforapproval;
import com.factory.PlaywrightFactory;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.procurement.nonPoc.interfaces.logout.ILogout;
import com.procurement.nonPoc.interfaces.requisitions.IPrSendForApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.nonPoc.interfaces.login.ILogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.nonPoc.constants.requisitions.LPrSendForApproval.*;

public class SendForApproval implements IPrSendForApproval {

    private Page page;
    private Properties properties;
    private ILogin iLogin;
    private ILogout iLogout;
    private SendForApproval() {
    }

//TODO Constructor
    public SendForApproval(ILogin iLogin, Properties properties, Page page, ILogout iLogout) {
        this.properties = properties;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
    }

    public void sendForApproval() {
        try {
        String title = properties.getProperty("currentTitle");
        iLogin.performLogin(properties.getProperty("requesterEmail"));

        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON);
        waitForLocator(sendForApprovalButtonLocator);
        sendForApprovalButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES);
        waitForLocator(yesButtonLocator);
        yesButtonLocator.click();
        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void SendForApproval() {
        String title = properties.getProperty("currentTitle");
        iLogin.performLogin(properties.getProperty("requesterEmail"));

        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle);
        waitForLocator(titleLocator);
        titleLocator.first().click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        String prRefId = page.locator("#referenceId").textContent();

//        page.locator("#btnSendApproval").click();
//        Response response = page.waitForResponse(
//                resp -> resp.url().startsWith("https://geps_hopes_yil.cormsquare.com/api/Approvals") && resp.status() == 200,
//                () -> {
//                    page.locator("//button[contains(text(), 'Yes')]").click();
//                }
//        );
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

        String PRRefereneNumber = page.locator("#referenceId").textContent();
        PlaywrightFactory.saveToPropertiesFile("PRReferenceNumber",PRRefereneNumber);

        iLogout.performLogout();
    }
}