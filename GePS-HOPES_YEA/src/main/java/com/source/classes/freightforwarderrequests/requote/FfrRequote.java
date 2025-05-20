package com.source.classes.freightforwarderrequests.requote;
import com.constants.freightforwarderrequests.LFfrQuote;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.freightforwarderrequests.IFfrQuote;
import com.source.interfaces.freightforwarderrequests.IFfrRequote;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.DETAILS_BUTTON;
import static com.constants.dispatchnotes.LDnAssign.LIST_CONTAINER;
import static com.constants.freightforwarderrequests.LFfrQuote.*;
import static com.constants.freightforwarderrequests.LFfrReQuote.*;

public class FfrRequote implements IFfrRequote {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    JsonNode jsonNode;
    IFfrQuote iFfrQuote;
    Page page;

    private FfrRequote(){
    }

//TODO Constructor
    public FfrRequote(ILogin iLogin, JsonNode jsonNode, IFfrQuote iFfrQuote, ILogout iLogout, Page page){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.iFfrQuote = iFfrQuote;
        this.iLogout = iLogout;
        this.page = page;
        this.logger = LoggerUtil.getLogger(FfrRequote.class);
    }

    public void requote(){
        try {
            String logisticsManager = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManager);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                    break;
                }
            }

            Locator requoteButtonLocator = page.locator(REQUOTE_BUTTON);
            requoteButtonLocator.click();

            Locator saveButtonLocator = page.locator(SAVE_BUTTON);
            saveButtonLocator.last().click();

            Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
            emailPopUpLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            iLogout.performLogout();

            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator ffrNavigationBarLocator = page.locator(FFR_NAVIGATION_BAR);
            ffrNavigationBarLocator.click();

            String dnReferenceId = jsonNode.get("dispatchNotes").get("dispatchNoteReferenceId").asText();
            List<String> containerList1 = page.locator(LFfrQuote.LIST_CONTAINER).allTextContents();
            for(String tr : containerList1){
                if(tr.contains(dnReferenceId)){
                    Locator detailsButtonLocator = page.locator(LFfrQuote.DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                    break;
                }
            }

            Locator sendReQuoteButtonLocator = page.locator(VENDOR_REQUOTE_BUTTON);
            sendReQuoteButtonLocator.click();

            Locator submitQuotationLocator = page.locator(SUBMIT_REQUOTE_BUTTON);
            submitQuotationLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Freight Forwarder Re-Quote", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Freight Forwarder Requests Requote function: {}", exception.getMessage());
        }
    }
}