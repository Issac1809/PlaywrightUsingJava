package com.source.classes.purchaseorderrequests.suspend;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorderrequests.IPorCreate;
import com.source.interfaces.purchaseorderrequests.IPorEdit;
import com.source.interfaces.purchaseorderrequests.IPorSuspend;
import com.source.interfaces.requestforquotations.ICeCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorSuspend.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class PorSuspend implements IPorSuspend {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IPorEdit iPorEdit;
    ICeCreate iCeCreate;
    IPorCreate iPorCreate;

    private PorSuspend(){
    }

//TODO Constructor
    public PorSuspend(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IPorEdit iPorEdit, ICeCreate iCeCreate, IPorCreate iPorCreate){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iPorEdit = iPorEdit;
        this.iCeCreate = iCeCreate;
        this.iPorCreate = iPorCreate;
        this.logger = LoggerUtil.getLogger(PorSuspend.class);
    }

    public void suspend(String type, String purchaseType){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator porNavigationBarLocator = page.locator(POR_NAVIGATION_BAR);
            porNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator suspendButtonLocator = page.locator(SUSPEND_BUTTON);
            suspendButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS_INPUT_LOCATOR);
            remarksLocator.fill("Suspended");

            Locator acceptLocator = page.locator(YES);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Suspend function: {}", exception.getMessage());
        }
    }

    public void suspendPorEdit(String type, String purchaseType){
        try {
            suspend(type, purchaseType);
            iPorEdit.porEdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Suspend Edit function: {}", exception.getMessage());
        }
    }

    public void suspendRfqOrPrEdit(String type, String purchaseType){
        try {
            suspend(type, purchaseType);
            if(purchaseType.equalsIgnoreCase("Catalog")) {
                iPorCreate.porCreateButtonForCatalog(type, purchaseType);
            } else {
                iCeCreate.commercialEvaluationButton(type);
                iPorCreate.porCreateButtonForNonCatalog(type);
                iPorCreate.justification();
            }
            iPorCreate.taxCode();
            iPorCreate.porNotes();
            iPorCreate.porCreate();
        } catch (Exception exception) {
            logger.error("Exception in POR Suspend RFQ or PR Edit function: {}", exception.getMessage());
        }
    }
}
