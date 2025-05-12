package com.source.classes.purchaseorderrequests.create;
import com.constants.purchaseorderrequests.LPorCreate;
import com.constants.requestforquotations.LQuoRequote;
import com.constants.requisitions.LPrEdit;
import com.factory.PlaywrightFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;
import com.source.interfaces.purchaseorderrequests.IPorCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import com.utils.rpa.salesordersync.PR_List_Flow;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorCreate.*;
import static com.constants.requestforquotations.LQuoRequote.*;
import static com.utils.GetTitleUtil.*;

public class PorCreate implements IPorCreate {

    Logger logger;
    PlaywrightFactory playwrightFactory;
    ObjectMapper objectMapper;
    JsonNode jsonNode;
    PR_List_Flow prListFlow;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    String appUrl;
    int status = 0;

//TODO Constructor
    private PorCreate(){
    }

    public PorCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PlaywrightFactory playwrightFactory, ObjectMapper objectMapper, PR_List_Flow prListFlow){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.objectMapper = objectMapper;
        this.playwrightFactory = playwrightFactory;
        this.prListFlow = prListFlow;
        this.logger = LoggerUtil.getLogger(PorCreate.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public void porCreateButtonForCatalog(String type, String purchaseType) {
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(LPorCreate.getTitle(title));
            titleLocator.first().click();

            if(type.equalsIgnoreCase("Sales")){
                Locator sendToYQuoteButtonLocator = page.locator(SEND_TO_Y_QUOTE_BUTTON);
                sendToYQuoteButtonLocator.click();

                Locator yesButtonLocator = page.locator(YES_BUTTON);
                yesButtonLocator.click();

                Thread.sleep(3000);

                prListFlow.prListFlow(purchaseType);

                iLogout.performLogout();

                String requesterMailId = jsonNode.get("mailIds").get("requesterEmail").asText();
                iLogin.performLogin(requesterMailId);

                titleLocator.first().click();

                Locator convertSmToOmButtonLocator = page.locator(CONVERT_SM_TO_OM_BUTTON);
                convertSmToOmButtonLocator.click();

                Locator departmentPicDropDownLocator = page.locator(DEPARTMENT_PIC_DROP_DOWN);
                departmentPicDropDownLocator.click();

//                Locator departmentPicSearchFieldLocator = page.locator(DEPARTMENT_PIC_SEARCH_FIELD);
//                departmentPicSearchFieldLocator.fill(requesterMailId);

                Locator departmentPicMailIdLocator = page.locator(getDepartmentPic(requesterMailId));
                departmentPicMailIdLocator.first().click();

                Locator updateButtonLocator = page.locator(LPorCreate.UPDATE_BUTTON);
                updateButtonLocator.click();

                Locator quantityMismatchPopup = page.locator(QUANTITY_MISMATCH_POPUP);
                if(quantityMismatchPopup.isVisible()) {
                    yesButtonLocator.click();

                    Locator editButtonLocator = page.locator(LPrEdit.EDIT_BUTTON);
                    editButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    Locator prUpdateButtonLocator = page.locator(LPrEdit.UPDATE_BUTTON);
                    prUpdateButtonLocator.click();

                    Locator submitButtonLocator = page.locator(LPorCreate.SUBMIT_BUTTON);
                    submitButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);

                    titleLocator.first().click();

                    Locator porCreateButtonLocator = page.locator(CATALOG_POR_CREATE_BUTTON);
                    porCreateButtonLocator.first().click();
                } else {
                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    yesButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);

                    titleLocator.first().click();

                    Locator porCreateButtonLocator = page.locator(CATALOG_POR_CREATE_BUTTON);
                    porCreateButtonLocator.first().click();
                }
            } else {
                Locator porCreateButtonLocator = page.locator(CATALOG_POR_CREATE_BUTTON);
                porCreateButtonLocator.first().click();
            }

            boolean advancePaymentFlag = jsonNode.get("purchaseOrderRequests").get("advancePaymentFlag").asBoolean();
            boolean milestonePaymentFlag = jsonNode.get("purchaseOrderRequests").get("milestonePaymentFlag").asBoolean();

            if(advancePaymentFlag || milestonePaymentFlag){
                advanceAndMilestonePayments(advancePaymentFlag, milestonePaymentFlag, type, purchaseType);
            }
        } catch (Exception exception) {
            logger.error("Exception in POR Create For Catalog Type Function: {}", exception.getMessage());
        }
    }

    public void porCreateButtonForNonCatalog(String type, String purchaseType) {
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator rfqNavigationBarLocator = page.locator(RFQ_NAVIGATION_BAR);
            rfqNavigationBarLocator.click();

            String title = getRFQTransactionTitle(type);
            Locator titleLocator = page.locator(LPorCreate.getTitle(title));
            titleLocator.first().click();

            if(type.equalsIgnoreCase("Sales")){
                Locator sendToYQuoteButtonLocator = page.locator(SEND_TO_Y_QUOTE_BUTTON);
                sendToYQuoteButtonLocator.click();

                Locator yesButtonLocator = page.locator(YES_BUTTON);
                yesButtonLocator.click();

                Thread.sleep(5000);

                prListFlow.prListFlow(purchaseType);

                iLogout.performLogout();

                String requesterMailId = jsonNode.get("mailIds").get("requesterEmail").asText();
                iLogin.performLogin(requesterMailId);

                rfqNavigationBarLocator.click();

                titleLocator.first().click();

                Locator convertSmToOmButtonLocator = page.locator(NON_CATALOG_CONVERT_SM_TO_OM_BUTTON);
                convertSmToOmButtonLocator.click();

                Locator departmentPicDropDownLocator = page.locator(DEPARTMENT_PIC_DROP_DOWN);
                departmentPicDropDownLocator.click();

//                Locator departmentPicSearchFieldLocator = page.locator(DEPARTMENT_PIC_SEARCH_FIELD);
//                departmentPicSearchFieldLocator.fill(requesterMailId);

                Locator departmentPicMailIdLocator = page.locator(getDepartmentPic(requesterMailId));
                departmentPicMailIdLocator.first().click();

                Locator updateButtonLocator = page.locator(LPorCreate.RFQ_UPDATE_BUTTON);
                updateButtonLocator.click();

                Locator quantityMismatchPopup = page.locator(QUANTITY_MISMATCH_POPUP);
                if(quantityMismatchPopup.isVisible()) {
                    yesButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);

                    rfqNavigationBarLocator.click();

                    titleLocator.first().click();

                    Locator requoteButtonLocator = page.locator(LQuoRequote.REQUOTE_BUTTON);
                    requoteButtonLocator.click();

                    Locator submitButtonLocator = page.locator(LPorCreate.SUBMIT_BUTTON);
                    submitButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    iLogout.performLogout();

                    String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
                    iLogin.performLogin(vendorMailId);

                    titleLocator.first().click();

                    Locator requoteEditButtonLocator = page.locator(REQUOTE_EDIT_BUTTON);
                    requoteEditButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    Locator updateButtonLocator1 = page.locator(LQuoRequote.UPDATE_BUTTON);
                    updateButtonLocator1.click();

                    Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
                    acceptLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);

                    rfqNavigationBarLocator.click();

                    titleLocator.first().click();

                    Locator porCreateButtonLocator = page.locator(NON_CATALOG_POR_CREATE_BUTTON);
                    porCreateButtonLocator.first().click();
                } else {
                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    yesButtonLocator.click();

                    page.waitForLoadState(LoadState.NETWORKIDLE);

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);

                    rfqNavigationBarLocator.click();

                    titleLocator.first().click();

                    Locator porCreateButtonLocator = page.locator(NON_CATALOG_POR_CREATE_BUTTON);
                    porCreateButtonLocator.first().click();
                }
            } else {
                Locator porCreateButtonLocator = page.locator(NON_CATALOG_POR_CREATE_BUTTON);
                porCreateButtonLocator.first().click();
            }

            boolean advancePaymentFlag = jsonNode.get("purchaseOrderRequests").get("advancePaymentFlag").asBoolean();
            boolean milestonePaymentFlag = jsonNode.get("purchaseOrderRequests").get("milestonePaymentFlag").asBoolean();

            if(advancePaymentFlag || milestonePaymentFlag){
                advanceAndMilestonePayments(advancePaymentFlag, milestonePaymentFlag, type, purchaseType);
            }

        } catch (Exception exception) {
            logger.error("Exception in POR Create For Non-Catalog Type Function: {}", exception.getMessage());
        }
    }

    public void advanceAndMilestonePayments(boolean advancePaymentFlag, boolean milestonePaymentFlag, String type, String purchaseType) {
        try {
            String advancePaymentPercentage = jsonNode.get("purchaseOrderRequests").get("advancePaymentPercentage").asText();
            String creditPeriodInDays = jsonNode.get("purchaseOrderRequests").get("creditPeriodInDays").asText();

            if(advancePaymentFlag){
                page.waitForLoadState(LoadState.NETWORKIDLE);
                Locator advancePaymentButtonLocator = page.locator(ADVANCE_PAYMENT_BUTTON);
                advancePaymentButtonLocator.click();

                Locator advancePaymentNameLocator = page.locator(ADVANCE_PAYMENT_NAME);
                advancePaymentNameLocator.fill("Advance Payment - 1");

                Locator advancePaymentPercentageLocator = page.locator(ADVANCE_PAYMENT_PERCENTAGE);
                advancePaymentPercentageLocator.fill(advancePaymentPercentage);

                Locator advancePaymentCreditPeriodInDaysLocator = page.locator(ADVANCE_PAYMENT_CREDIT_PERIOD_IN_DAYS);
                advancePaymentCreditPeriodInDaysLocator.clear();
                advancePaymentCreditPeriodInDaysLocator.fill(creditPeriodInDays);

                Locator submitButtonLocator = page.locator(SUBMIT_ADVANCE_PAYMENT_BUTTON);
                submitButtonLocator.click();

                //TODO Milestone Payment
                int milestoneCount = jsonNode.get("purchaseOrderRequests").get("milestonePaymentCount").asInt();
                int reminder = 100 % milestoneCount;
                int percentage = 100 / milestoneCount;

                for(int i = 1; i <= milestoneCount; i++){
                    Locator milestoneButtonLocator = page.locator(MILESTONE_PAYMENT_BUTTON);
                    milestoneButtonLocator.click();

                    Locator milestonePaymentNameLocator;
                    if(purchaseType.equalsIgnoreCase("Catalog")){
                        milestonePaymentNameLocator = page.locator(CATALOG_MILESTONE_PAYMENT_NAME);
                    } else {
                        milestonePaymentNameLocator = page.locator(NON_CATALOG_MILESTONE_PAYMENT_NAME);
                    }

                    milestonePaymentNameLocator.fill("Milestone - " + i);

                    Locator milestonePaymentPercentageLocator = page.locator(MILESTONE_PAYMENT_PERCENTAGE);
                    if(i == milestoneCount){
                        milestonePaymentPercentageLocator.fill(String.valueOf(percentage + reminder));
                    } else {
                        milestonePaymentPercentageLocator.fill(String.valueOf(percentage));
                    }
                    Locator submitButtonLocator1 = page.locator(SUBMIT_MILESTONE_PAYMENT_BUTTON);
                    submitButtonLocator1.click();
                }
            } else if (milestonePaymentFlag) {
                page.waitForLoadState(LoadState.NETWORKIDLE);
                int milestoneCount = jsonNode.get("purchaseOrderRequests").get("milestonePaymentCount").asInt();
                int reminder = 100 % milestoneCount;
                int percentage = 100 / milestoneCount;

                for(int i = 1; i <= milestoneCount; i++){
                    Locator milestoneButtonLocator = page.locator(MILESTONE_PAYMENT_BUTTON);
                    milestoneButtonLocator.click();

                    Locator milestonePaymentNameLocator;
                    if(purchaseType.equalsIgnoreCase("Catalog")){
                        milestonePaymentNameLocator = page.locator(CATALOG_MILESTONE_PAYMENT_NAME);
                    } else {
                        milestonePaymentNameLocator = page.locator(NON_CATALOG_MILESTONE_PAYMENT_NAME);
                    }

                    milestonePaymentNameLocator.fill("Milestone - " + i);

                    Locator milestonePaymentPercentageLocator = page.locator(MILESTONE_PAYMENT_PERCENTAGE);
                    if(i == milestoneCount){
                        milestonePaymentPercentageLocator.fill(String.valueOf(percentage + reminder));
                    } else {
                        milestonePaymentPercentageLocator.fill(String.valueOf(percentage));
                    }
                    Locator submitButtonLocator1 = page.locator(SUBMIT_MILESTONE_PAYMENT_BUTTON);
                    submitButtonLocator1.click();
                }
            }
        } catch (Exception exception) {
            logger.error("Exception in Advance and Milestone Payments Function: {}", exception.getMessage());
        }
    }

    public void justification(){
        try {
            Locator below5lLocator = page.locator(BELOW_5L);
            below5lLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in Choose Justification Function: {}", exception.getMessage());
        }
    }

    public void taxCode(){
        try {
            Locator selectTaxCodeLocator = page.getByText(TAX_CODE);
            selectTaxCodeLocator.last().click();

            String taxCode = jsonNode.get("purchaseOrderRequests").get("taxCode").asText();
            Locator taxCodeLocator = page.locator(getTaxCode(taxCode));
            taxCodeLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in POR Create Tax Code Function: {}", exception.getMessage());
        }
    }

    public void porNotes() {
        try {
            String notes = jsonNode.get("purchaseOrderRequests").get("purchaseOrderNotes").asText();
            Locator porNotesLocator = page.locator(POR_NOTES);
            porNotesLocator.fill(notes);
        } catch (Exception exception) {
            logger.error("Exception in POR Notes Function: {}", exception.getMessage());
        }
    }

    public int createButton(String type){
        int status = 0;
        try {
            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES);

            String porType = type.equalsIgnoreCase("PS") ? "/api/PurchaseOrderRequests/" : "/api/PurchaseOrderRequestsSales/";

            Response createResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + porType) && response.status() == 200,
                    yesButtonLocator::click
            );
            status = createResponse.status();

            String url = page.url();
            String[] urlArray = url.split("=");
            String getUid = urlArray[1];
            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrderRequests", "purchaseOrderRequestUid", getUid);

            APIResponse apiResponse = page.request().fetch(appUrl + porType + getUid, RequestOptions.create());
            JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
            String purchaseOrderRequestId = jsonNode.get("id").asText();
            String porReferenceNumber = jsonNode.get("referenceId").asText();
            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrderRequests", "purchaseOrderRequestId", purchaseOrderRequestId);
            playwrightFactory.savePropertiesIntoJsonFile("purchaseOrderRequests", "porReferenceNumber", porReferenceNumber);

            status = apiResponse.status();

            page.waitForLoadState(LoadState.NETWORKIDLE);

            PlaywrightFactory.attachScreenshotWithName("Purchase Order Request Create", page);

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Create Button Function: {}", exception.getMessage());
        }
        return status;
    }

    public int porCreate(String type, String purchaseType) {
        int status = 0;
        try {
            if (purchaseType.equalsIgnoreCase("Catalog")) {
                porCreateButtonForCatalog(type, purchaseType);
            } else if (purchaseType.equalsIgnoreCase("NonCatalog")) {
                porCreateButtonForNonCatalog(type, purchaseType);
                justification();
            }
            taxCode();
            porNotes();
            status = createButton(type);
        }catch (Exception exception) {
            logger.error("Exception in POR Create Function: {}", exception.getMessage());
        }
        return status;
    }
}