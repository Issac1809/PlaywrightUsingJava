package com.source.classes.freightforwarderrequests.quote;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.freightforwarderrequests.IFfrQuote;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.freightforwarderrequests.LFfrQuote.*;

public class FfrQuote implements IFfrQuote {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private FfrQuote(){
    }

//TODO Constructor
    public FfrQuote(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(FfrQuote.class);
    }

    public void quote() {
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator ffrNavigationBarLocator = page.locator(FFR_NAVIGATION_BAR);
            ffrNavigationBarLocator.click();

            String dnReferenceId = jsonNode.get("dispatchNotes").get("dispatchNoteReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(dnReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                }
            }

            Locator sendQuoteButtonLocator = page.locator(SEND_QUOTE_BUTTON);
            sendQuoteButtonLocator.click();

            String totalChargeableWeight = jsonNode.get("dispatchNotes").get("totalChargeableWeightKg").asText();
            Locator totalChargeableWeightLocator = page.locator(TOTAL_CHARGEABLE_WEIGHT);
            totalChargeableWeightLocator.fill(totalChargeableWeight);

            String unitRate = jsonNode.get("dispatchNotes").get("unitRate").asText();
            Locator unitRateLocator = page.locator(UNIT_RATE);
            unitRateLocator.fill(unitRate);

            Locator submitQuotationLocator = page.locator(SUBMIT_BUTTON);
            submitQuotationLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Freight Forwarder Requests Quote function: {}", exception.getMessage());
        }
    }
}