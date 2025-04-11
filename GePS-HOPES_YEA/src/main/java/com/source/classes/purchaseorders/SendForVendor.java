package com.source.classes.purchaseorders;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.purchaseorders.IPoSendForVendor;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorCreate.getTitle;
import static com.constants.purchaseorders.LPoSendForVendor.*;
import static com.utils.GetTitleUtil.getTransactionTitle;

public class SendForVendor implements IPoSendForVendor {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private SendForVendor(){
    }

//TODO Constructor
    public SendForVendor(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(SendForVendor.class);
    }

    public void sendPoForVendor(String type, String purchaseType){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator poNavigationBarLocator = page.locator(PO_NAVIGATION_BAR);
            poNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator sendForVendorButtonLocator = page.locator(SEND_FOR_VENDOR_BUTTON);
            sendForVendorButtonLocator.click();

            Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
            emailPopUpLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Send PO For Vendor function: {}", exception.getMessage());
        }
    }
}