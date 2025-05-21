package com.source.classes.inspections.readyforinspection;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.inspections.IInsReadyForInspection;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.inspections.LInsCreate.ACCEPT_BUTTON;
import static com.constants.inspections.LInsReadyForInspection.*;

public class InsReadyForInspection implements IInsReadyForInspection {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;

    private InsReadyForInspection(){
    }

//TODO Constructor
    public InsReadyForInspection(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(InsReadyForInspection.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int readyForInspection(){
        int status =0;
        try {
            String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorMailId);

            Locator osNavigationBarLocator = page.locator(OS_NAVIGATION_BAR);
            osNavigationBarLocator.click();

            String poReferenceId = jsonNode.get("purchaseOrders").get("poReferenceId").asText();
            List<String> containerList = page.locator(LIST_CONTAINER).allTextContents();
            for(String tr : containerList){
                if(tr.contains(poReferenceId)){
                    Locator detailsButtonLocator = page.locator(DETAILS_BUTTON);
                    detailsButtonLocator.first().click();
                    break;
                }
            }

            Locator readyForInspectionButtonLocator = page.locator(READY_FOR_INSPECTION_BUTTON);
            readyForInspectionButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);

            Response osResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/VP/OrderSchedules/") && response.status() == 200,
                    acceptButtonLocator.first()::click
            );
            status = osResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Ready For Inspection", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Ready for Inspection function: {}", exception.getMessage());
        }
        return status;
    }
}