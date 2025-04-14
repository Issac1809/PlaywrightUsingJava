package com.source.classes.requestforquotations.create;
import com.constants.requestforquotations.LCeCreate;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.IRfqCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LRfqCreate.*;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class RfqCreate implements IRfqCreate {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    private String appUrl;
    PlaywrightFactory playwrightFactory;

    private RfqCreate(){
    }

//TODO Constructor
    public RfqCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(RfqCreate.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
        this.playwrightFactory = playwrightFactory;
    }

    public int buyerRfqCreate(String type) {
        int status = 0;
        try {
            String buyerEmailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            String rfqNotes = jsonNode.get("requestForQuotation").get("rfqNotes").asText();

            iLogin.performLogin(buyerEmailId);

            String title = getRFQTransactionTitle(type);
            Locator titleLocator = page.locator(LCeCreate.getTitle(title));
            titleLocator.first().click();

            page.locator(CREATE_RFQ_BUTTON).click();
            page.waitForLoadState(LoadState.NETWORKIDLE);

            Locator notesLocator = page.locator(NOTES);
            notesLocator.fill(rfqNotes);

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES_BUTTON);

            String reqType = type.equalsIgnoreCase("PS") ? "/api/RequestForQuotations/" : "/api/RequestForQuotationsOthers/";
            Response createResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                    yesButtonLocator::click
            );

            status = createResponse.status();

            String url = page.url();
            String[] urlArray = url.split("=");
            String getUid = urlArray[1];
            playwrightFactory.savePropertiesIntoJsonFile("requestForQuotation", "requestForQuotationUid", getUid);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Buyer RFQ Create Function: {}", exception.getMessage());
        }
        return status;
    }
}