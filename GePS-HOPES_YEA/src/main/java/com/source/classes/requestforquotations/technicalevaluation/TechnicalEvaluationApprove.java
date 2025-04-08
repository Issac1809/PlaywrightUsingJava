package com.source.classes.requestforquotations.technicalevaluation;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.ITeApprove;
import com.source.interfaces.requestforquotations.ITeCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LTeCreate.*;
import static com.constants.requestforquotations.LTeReject.REMARKS_INPUT_LOCATOR;

public class TechnicalEvaluationApprove implements ITeApprove {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    ITeCreate iTeCreate;


    private TechnicalEvaluationApprove(){
    }

//TODO Constructor
    public TechnicalEvaluationApprove(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, ITeCreate iTeCreate){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.iTeCreate = iTeCreate;
        this.logger = LoggerUtil.getLogger(TechnicalEvaluationApprove.class);
    }

    public void technicalEvaluationApprove(String type) {
        try {
        iTeCreate.technicalEvaluationCreate(type);

        Locator approveButtonLocator = page.locator(APPROVE_BUTTON);
        approveButtonLocator.click();

        Locator remarksInputLocator = page.locator(REMARKS_INPUT_LOCATOR);
        remarksInputLocator.fill("TE Approved");

        Locator acceptLocator = page.locator(YES);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Technical Evaluation Approve Function: {}", exception.getMessage());
        }
    }
}