package com.source.classes.dispatchnotes.dnreturn;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.dispatchnotes.IDnEdit;
import com.source.interfaces.dispatchnotes.IDnReturn;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnReturn.*;

public class DnReturn implements IDnReturn {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IDnEdit iDnEdit;

    private DnReturn(){
    }

//TODO Constructor
    public DnReturn(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IDnEdit iDnEdit){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iDnEdit = iDnEdit;
        this.logger = LoggerUtil.getLogger(DnReturn.class);
    }

    public void dnReturn() {
        try {
            String logisticsManagerMailId = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManagerMailId);

            Locator dnNavigationBarLocator = page.locator(DN_NAVIGATION_BAR);
            dnNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                }
            }

            Locator dropDownLocator = page.locator(DROP_DOWN);
            dropDownLocator.click();

            Locator returnButtonLocator = page.locator(RETURN_BUTTON);
            returnButtonLocator.click();

            Locator remarksLocator = page.locator(REMARKS_FIELD);
            remarksLocator.fill("Returned");

            Locator acceptLocator = page.locator(ACCEPT_BUTTON);
            acceptLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Dispatch Notes Return", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Return function: {}", exception.getMessage());
        }
    }
}