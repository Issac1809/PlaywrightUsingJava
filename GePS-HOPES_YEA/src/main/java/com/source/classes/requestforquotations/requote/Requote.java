package com.source.classes.requestforquotations.requote;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.IQuoRequote;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LQuoRequote.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class Requote implements IQuoRequote {

    Logger logger;
    ILogin iLogin;
    JsonNode jsonNode;
    Page page;
    ILogout iLogout;
    private String appUrl;

    private Requote(){
    }

//TODO Constructor
    public Requote(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Requote.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int requote(String type){
        int status = 0;
        try {
        String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarButtonLocator = page.locator(RFQ_NAVIGATION_BAR_BUTTON);
        rfqNavigationBarButtonLocator.click();

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator requoteButtonLocator = page.locator(REQUOTE_BUTTON);
        requoteButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
        acceptLocator.click();

        Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
        emailPopUpLocator.click();

        iLogout.performLogout();

        String vendorEmailId = jsonNode.get("mailIds").get("vendorEmail").asText();
        iLogin.performLogin(vendorEmailId);

        Locator getTitleLocator1 = page.locator(getTitle(title));
        getTitleLocator1.first().click();

        Locator requoteEditButton = page.locator(REQUOTE_EDIT_BUTTON);
        requoteEditButton.click();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        Locator updateButtonLocator = page.locator(UPDATE_BUTTON);
        updateButtonLocator.click();

        Locator acceptLocator1 = page.locator(ACCEPT_REMARKS_POP_UP);

        String reqType = type.equalsIgnoreCase("PS") ? "/api/Vp/Quotation/" : "/api/Vp/QuotationSales/";

        Response submitResponse = page.waitForResponse(
                response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                acceptLocator::click
        );
        status = submitResponse.status();

        page.waitForLoadState(LoadState.NETWORKIDLE);

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Requote Function: {}", exception.getMessage());
        }
        return status;
    }
}