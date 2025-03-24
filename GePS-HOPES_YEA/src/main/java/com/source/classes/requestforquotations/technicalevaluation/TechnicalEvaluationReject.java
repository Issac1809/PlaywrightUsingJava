package com.source.classes.requestforquotations.technicalevaluation;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotation.ITeCreate;
import com.source.interfaces.requestforquotation.ITeReject;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LTeReject.*;

public class TechnicalEvaluationReject implements ITeReject {

    Logger logger;
    ILogin iLogin;
    JsonNode jsonNode;
    Page page;
    ILogout iLogout;
    ITeCreate iTeCreate;

    private TechnicalEvaluationReject(){
    }

//TODO Constructor
    public TechnicalEvaluationReject(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, ITeCreate iTeCreate){
        this.iLogin = iLogin;
        this.page = page;
        this.jsonNode = jsonNode;
        this.iLogout = iLogout;
        this.iTeCreate = iTeCreate;
        this.logger = LoggerUtil.getLogger(TechnicalEvaluationReject.class);
    }

    public void technicalEvaluationReject(String type){
        try {
        iTeCreate.technicalEvaluationCreate(type);

        Locator rejectButtonLocator = page.locator(REJECT_BUTTON);
        rejectButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT_LOCATOR);
        remarksInputLocator.fill("TE Rejected");

        Locator acceptLocator = page.locator(YES);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Technical Evaluation Reject Function: {}", exception.getMessage());
        }
    }
}