package com.source.classes.requestforquotations.suspend;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotation.IRfqCreate;
import com.source.interfaces.requestforquotation.IRfqEdit;
import com.source.interfaces.requestforquotation.IRfqSuspend;
import com.source.interfaces.requisitions.IPrApprove;
import com.source.interfaces.requisitions.IPrAssign;
import com.source.interfaces.requisitions.IPrEdit;
import com.source.interfaces.requisitions.IPrSendForApproval;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LRfqSuspend.*;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class RfqSuspend implements IRfqSuspend {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    Page page;
    IRfqEdit iRfqEdit;
    IPrEdit iPrEdit;
    IPrSendForApproval iPrSendForApproval;
    IPrApprove iPrApprove;
    IPrAssign iPrAssign;
    IRfqCreate iRfqCreate;

    private RfqSuspend(){
    }

//TODO Constructor
    public RfqSuspend(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IRfqEdit iRfqEdit, IPrEdit iPrEdit,
                      IPrSendForApproval iPrSendForApproval, IPrApprove iPrApprove, IPrAssign iPrAssign, IRfqCreate iRfqCreate){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iRfqEdit = iRfqEdit;
        this.iPrEdit = iPrEdit;
        this.iPrSendForApproval = iPrSendForApproval;
        this.iPrApprove = iPrApprove;
        this.iPrAssign = iPrAssign;
        this.iRfqCreate = iRfqCreate;
    }

    public void suspendRfqEdit(String type) {
        try {
        String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
        rfqNavigationBarLocator.click();

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        suspendButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        remarksInputLocator.fill("Suspended");

        Locator acceptLocator = page.locator(YES);
        acceptLocator.click();

        iLogout.performLogout();

        iRfqEdit.rfqEditMethod(type);
        } catch (Exception exception) {
            logger.error("Exception in Suspend RFQ Edit Function: {}", exception.getMessage());
        }
    }

    public void suspendPREdit(String type, String purchaseType) {
        try {
        String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
        rfqNavigationBarLocator.click();

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
        suspendButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT);
        remarksInputLocator.fill("Suspended");

        Locator acceptLocator = page.locator(YES);
        acceptLocator.click();

        iLogout.performLogout();

        iPrEdit.edit(type, purchaseType);
        iPrSendForApproval.sendForApproval(type, purchaseType);
        iPrApprove.approve(type, purchaseType);
        iPrAssign.buyerManagerAssign(type, purchaseType);
        iRfqCreate.buyerRfqCreate(type);
        } catch (Exception exception) {
            logger.error("Exception in Suspend RFQ and PR Edit Function: {}", exception.getMessage());
        }
    }
}