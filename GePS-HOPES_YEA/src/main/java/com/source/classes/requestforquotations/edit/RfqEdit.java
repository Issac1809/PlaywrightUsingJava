package com.source.classes.requestforquotations.edit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.IRfqEdit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LRfqEdit.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class RfqEdit implements IRfqEdit {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    Page page;
    private String appUrl;


    private RfqEdit(){
    }

//TODO Constructor
    public RfqEdit(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(RfqEdit.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int rfqEditMethod(String type) {
        int status = 0;
        try {
        String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
        rfqNavigationBarLocator.click();

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator editButtonLocator = page.locator(EDIT_BUTTON);
        editButtonLocator.click();
        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        updateButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_POP_UP);
        remarksLocator.fill(REMARKS);

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
//        acceptLocator.click();

        String reqType = type.equalsIgnoreCase("PS") ? "/api/RequestForQuotations/" : "/api/RequestForQuotationsOthers/";

        Response editResponse = page.waitForResponse(
                response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                acceptLocator::click
        );
        status = editResponse.status();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in RFQ Edit Function: {}", exception.getMessage());
        }
        return status;
    }
}