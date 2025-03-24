package com.source.classes.requestforquotations.readyforevaluation;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotation.IReadyForEvalutation;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LReadyForEvaluation.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class ReadyForEvaluation implements IReadyForEvalutation {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private ReadyForEvaluation(){
    }

//TODO Constructor
    public ReadyForEvaluation(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void readyForEvaluationButton(String type){
        try {
        String buyerMailId = jsonNode.get("maillIds").get("buyerEmail").asText();
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
        rfqNavigationBarLocator.click();

        String title = getRFQTransactionTitle(type);
        Locator titleLocator = page.locator(getTitle(title));
        titleLocator.first().click();

        Locator readyForEvaluationButtonLocator = page.locator(READY_FOR_EVALUATION_BUTTON);
        readyForEvaluationButtonLocator.click();

        Locator acceptLocator = page.locator(YES);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Ready For Evaluation Button Function: {}", exception.getMessage());
        }
    }
}