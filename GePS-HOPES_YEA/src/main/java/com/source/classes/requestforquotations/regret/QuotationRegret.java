package com.source.classes.requestforquotations.regret;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.*;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.IQuoRegret;
import com.source.interfaces.requestforquotations.IQuoSubmit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LQuoRegret.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class QuotationRegret implements IQuoRegret {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    IQuoSubmit iQuoSubmit;
    JsonNode jsonNode;
    Page page;
    private String appUrl;

    private QuotationRegret(){
    }

//TODO Constructor
    public QuotationRegret(IQuoSubmit iQuoSubmit, ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iQuoSubmit = iQuoSubmit;
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(QuotationRegret.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int regret(String type){
        int status = 0;
        try {
        iQuoSubmit.inviteRegisteredVendor(type);

        String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
        iLogin.performLogin(vendorMailId);

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator regretButtonLocator = page.locator(REGRET_BUTTON);
        regretButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_POP_UP);
        remarksLocator.fill(REMARKS);

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);

        String reqType = type.equalsIgnoreCase("PS") ? "/api/VP/RequestForQuotations/" : "/api/VP/RequestForQuotationsSales/";

        Response regretResponse = page.waitForResponse(
                response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                acceptLocator::click
        );
        status = regretResponse.status();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Quotation Regret Function: {}", exception.getMessage());
        }
        return status;
    }
}