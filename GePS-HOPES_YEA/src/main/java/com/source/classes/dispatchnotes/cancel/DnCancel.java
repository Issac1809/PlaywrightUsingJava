package com.source.classes.dispatchnotes.cancel;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.dispatchnotes.IDnCancel;
import com.source.interfaces.dispatchnotes.IDnCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnCancel.*;

public class DnCancel implements IDnCancel {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    IDnCreate iDnCreate;

    private DnCancel(){
    }

//TODO Constructor
    public DnCancel(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, IDnCreate iDnCreate){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iDnCreate = iDnCreate;
        this.logger = LoggerUtil.getLogger(DnCancel.class);
    }

    public void cancel(String type, String purchaseType) {
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

            Locator cancelButtonLocator = page.locator(CANCEL_BUTTON);
            cancelButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            PlaywrightFactory.attachScreenshotWithName("Dispatch Notes Cancel", page);

            iLogout.performLogout();

            iDnCreate.create(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Cancel function: {}", exception.getMessage());
        }
    }
}