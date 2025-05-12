package com.source.classes.requestforquotations.technicalevaluation;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotations.ITeCreate;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.requestforquotations.LRfqSuspend.getTitle;
import static com.constants.requestforquotations.LTeCreate.*;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class TechnicalEvaluationCreate implements ITeCreate  {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    private String appUrl;

    private TechnicalEvaluationCreate(){
    }

//TODO Constructor
    public TechnicalEvaluationCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(TechnicalEvaluationCreate.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public int technicalEvaluationCreate(String type) {
        int status = 0;
        try {
            String requesterMailId = jsonNode.get("mailIds").get("requesterEmail").asText();
            iLogin.performLogin(requesterMailId);

            Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
            rfqNavigationBarLocator.click();

            String title = getRFQTransactionTitle(type);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator teCreateButtonLocator = page.locator(TE_CREATE_BUTTON);
            teCreateButtonLocator.click();

            Locator vendorSelectCheckboxLocator = page.locator(VENDOR_SELECT_CHECKBOX);
            vendorSelectCheckboxLocator.click();

            Locator createTeButtonLocator = page.locator(CREATE_TECHNICAL_EVALUATION_BUTTON);
            createTeButtonLocator.click();

            Locator remarksAccept = page.locator(YES);
            remarksAccept.click();

            Locator sendForApprovalLocator = page.locator(SEND_FOR_APPROVAL);
            sendForApprovalLocator.click();

            Locator teApproverSelectLocator = page.locator(APPROVER_SELECT);
            teApproverSelectLocator.first().click();

            String teApprover = jsonNode.get("mailIds").get("requesterEmail").asText();
            Locator teApproverSearchLocator = page.locator(SEARCH_FIELD);
            teApproverSearchLocator.fill(teApprover);

            Locator getTeApproverLocator = page.locator(getTeApprover(teApprover));
            getTeApproverLocator.click();

            Locator saveApproverLocator = page.locator(SAVE_APPROVER);
            saveApproverLocator.click();

            Locator acceptLocator = page.locator(YES);

            Response submitResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + "/api/TechnicalEvaluations/") && response.status() == 200,
                    acceptLocator::click
            );
            status = submitResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Technical Evaluation Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Technical Evaluation Create Function: {}", exception.getMessage());
        }
        return status;
    }
}