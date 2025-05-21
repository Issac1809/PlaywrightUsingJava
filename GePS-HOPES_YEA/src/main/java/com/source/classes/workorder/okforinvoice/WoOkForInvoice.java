package com.source.classes.workorder.okforinvoice;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.workorders.IWoOkForInvoice;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.workorders.LOkForInvoice.*;

public class WoOkForInvoice implements IWoOkForInvoice {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    PlaywrightFactory playwrightFactory;

    private WoOkForInvoice(){
    }

//TODO Constructor
    public WoOkForInvoice(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory){
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogin = iLogin;
        this.iLogout = iLogout;
        this.playwrightFactory = playwrightFactory;
        this.logger = LoggerUtil.getLogger(WoOkForInvoice.class);
    }

    public void okForInvoice() {
        try {
            String logisticsManagerMailId = jsonNode.get("mailIds").get("logisticsManagerEmail").asText();
            iLogin.performLogin(logisticsManagerMailId);

            Locator workOrderNavigationBarLocator = page.locator(WO_NAVIGATION_BAR);
            workOrderNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                    break;
                }
            }

            Locator okForInvoiceButton = page.locator(OK_FOR_INVOICE_BUTTON);
            okForInvoiceButton.click();

            Locator yesButtonLocator = page.locator(YES_BUTTON);
            yesButtonLocator.click();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Work Order Ok For Invoice", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Edit Function: {}", exception.getMessage());
        }
    }
}