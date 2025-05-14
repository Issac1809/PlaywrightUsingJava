package com.source.classes.purchaseorderrequests.reject;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.purchaseorderrequests.IPorSendForApproval;
import com.microsoft.playwright.Page;
import com.source.interfaces.purchaseorderrequests.IPorReject;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorderrequests.IPorEdit;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.purchaseorderrequests.LPorReject.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorReject implements IPorReject {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    Page page;
    IPorEdit iPorEdit;
    IPorSendForApproval iPorSendForApproval;
    String appUrl;


    private PorReject(){
    }

//TODO Constructor
    public PorReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IPorEdit iPorEdit, IPorSendForApproval iPorSendForApproval){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iPorEdit = iPorEdit;
        this.iPorSendForApproval = iPorSendForApproval;
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int porReject(String type, String purchaseType) {
        int status = 0;
        try {
            String approver = jsonNode.get("purchaseOrderRequests").get("approvers").asText();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            iLogin.performLogin(approver);

            Locator porNavigationBar = page.locator(POR_NAVIGATION_BAR);
            porNavigationBar.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
            rejectButtonLocator.click();

            Locator remarksInputLocator = page.locator(REMARKS_INPUT);
            remarksInputLocator.fill("Updated");

            Locator acceptLocator = page.locator(YES);
            String porType = type.equalsIgnoreCase("PS") ? "/api/PurchaseOrderRequests/" : "/api/PurchaseOrderRequestsSales/";

            Response rejectResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + porType) && response.status() == 200,
                    acceptLocator::click
            );
            status = rejectResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Request Reject", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Reject function: {}", exception.getMessage());
        }
        return status;
    }
}