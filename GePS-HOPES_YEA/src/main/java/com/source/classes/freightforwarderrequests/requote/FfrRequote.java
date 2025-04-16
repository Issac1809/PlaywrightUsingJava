package com.source.classes.freightforwarderrequests.requote;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.freightforwarderrequests.IFfrQuote;
import com.source.interfaces.freightforwarderrequests.IFfrRequote;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.DETAILS_BUTTON;
import static com.constants.dispatchnotes.LDnAssign.LIST_CONTAINER;
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

            Locator dnNavigationBarLocator = page.locator(FFR_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                }
            }

            iFfrQuote.quote();
        } catch (Exception exception) {
            logger.error("Exception in Freight Forwarder Requests Requote function: {}", exception.getMessage());
        }
    }
}