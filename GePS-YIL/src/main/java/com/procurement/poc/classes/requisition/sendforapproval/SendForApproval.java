package com.procurement.poc.classes.requisition.sendforapproval;
import com.procurement.poc.interfaces.logout.ILogout;
import com.procurement.poc.interfaces.requisitions.IPrSendForApproval;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.procurement.poc.interfaces.login.ILogin;

import java.util.Properties;
import static com.factory.PlaywrightFactory.waitForLocator;
import static com.procurement.poc.constants.requisitions.LPrSendForApproval.*;

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
        String title = properties.getProperty("orderTitle");
        iLogin.performLogin(properties.getProperty("requesterEmail"));

        String getTitle = getTitle(title);
        Locator titleLocator = page.locator(getTitle).first();
        waitForLocator(titleLocator);
        titleLocator.first().click();

        Locator sendForApprovalButtonLocator = page.locator(SEND_FOR_APPROVAL_BUTTON.getLocator());
        waitForLocator(sendForApprovalButtonLocator);
        sendForApprovalButtonLocator.click();

        Locator yesButtonLocator = page.locator(YES.getLocator());
        waitForLocator(yesButtonLocator);
        yesButtonLocator.click();
        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}