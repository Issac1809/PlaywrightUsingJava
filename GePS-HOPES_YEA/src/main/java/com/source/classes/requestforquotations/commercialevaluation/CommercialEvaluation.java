package com.source.classes.requestforquotations.commercialevaluation;
import com.constants.requestforquotations.LCeCreate;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.ICeCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LCeCreate.*;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class CommercialEvaluation implements ICeCreate {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private CommercialEvaluation(){
    }

//TODO Constructor
    public CommercialEvaluation(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(CommercialEvaluation.class);
    }

    public int commercialEvaluationButton(String type){
        int status = 0;
        try {
            String buyerEmailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            String uid = jsonNode.get("requisition").get("requestForQuotationUid").asText();

            iLogin.performLogin(buyerEmailId);

            Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
            rfqNavigationBarLocator.click();

            String title = getRFQTransactionTitle(type);
            Locator titleLocator = page.locator(LCeCreate.getTitle(title));
            titleLocator.first().click();

            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator selectionStatusLocator = page.locator(SELECTION_CRITERIA);
            selectionStatusLocator.click();
            selectionStatusLocator.selectOption(OPTION);

            Locator submitButtonLocator = page.locator(SUBMIT_BUTTON);
            submitButtonLocator.click();

            Locator acceptButtonLocator = page.locator(ACCEPT_BUTTON);
            acceptButtonLocator.click();

            APIResponse updateResponse = page.request().fetch("https://geps_hopes_yea.cormsquare.com/api/VP/RequestForQuotations/all/" + uid, RequestOptions.create());
            status = updateResponse.status();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Commercial Evaluation Create Function: {}", exception.getMessage());
        }
        return status;
    }
}