package com.source.classes.freightforwarderrequests.invite;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.freightforwarderrequests.IFfrInvite;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static com.constants.dispatchnotes.LDnAssign.DETAILS_BUTTON;
import static com.constants.dispatchnotes.LDnAssign.LIST_CONTAINER;
import static com.constants.freightforwarderrequests.LFfrInvite.*;

public class FfrInvite implements IFfrInvite {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private FfrInvite(){
    }

//TODO Constructor
    public FfrInvite(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(FfrInvite.class);
    }

    public void invite() {
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
                }
            }

            Locator inviteFreightForwarderButtonLocator = page.locator(INVITE_VENDOR_BUTTON);
            inviteFreightForwarderButtonLocator.click();

            Locator dropDownLocator = page.locator(DROP_DOWN);
            dropDownLocator.click();

            String freightVendor = jsonNode.get("mailIds").get("vendorEmail").asText();
            Locator searchFieldLocator = page.locator(SEARCH_FIELD);
            searchFieldLocator.fill(freightVendor);

            Locator freightForwarderLocator = page.locator(getFreightForwarder(freightVendor));
            freightForwarderLocator.click();

            Locator saveButtonLocator = page.locator(SAVE_BUTTON);
            saveButtonLocator.click();

            Locator emailPopUpLocator = page.locator(EMAIL_POP_UP);
            emailPopUpLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Freight Forwarder Requests Invite function: {}", exception.getMessage());
        }
    }
}