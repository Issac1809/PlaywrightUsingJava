package com.source.classes.requestforquotations.regret;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotation.IQuoRegret;
import com.source.interfaces.requestforquotation.IQuoSubmit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LQuoRegret.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class QuotationRegret implements IQuoRegret {

    Logger logger;
    ILogin iLogin;
    ILogout iLogout;
    IQuoSubmit iQuoSubmit;
    JsonNode jsonNode;
    Page page;

    private QuotationRegret(){
    }

//TODO Constructor
    public QuotationRegret(IQuoSubmit iQuoSubmit, ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iQuoSubmit = iQuoSubmit;
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(QuotationRegret.class);
    }

    public void regret(String type){
        try {
        iQuoSubmit.inviteRegisteredVendor(type);

        String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
        iLogin.performLogin(vendorMailId);

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator regretButtonLocator = page.locator(REGRET_BUTTON);
        regretButtonLocator.click();

        Locator remarksLocator = page.locator(REMARKS_POP_UP);
        remarksLocator.fill(REMARKS);

        Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Quotation Regret Function: {}", exception.getMessage());
        }
    }
}