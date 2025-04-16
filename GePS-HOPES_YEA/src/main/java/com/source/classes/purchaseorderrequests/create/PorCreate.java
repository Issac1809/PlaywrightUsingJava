package com.source.classes.purchaseorderrequests.create;
import com.constants.purchaseorderrequests.LPorCreate;
import com.constants.requestforquotations.LQuoRequote;
import com.constants.requisitions.LPrEdit;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.source.interfaces.purchaseorderrequests.IPorCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import com.utils.rpa.salesordersync.PR_List_Flow;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorCreate.*;
import static com.constants.requestforquotations.LQuoRequote.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.*;

public class PorCreate implements IPorCreate {

    Logger logger;
    JsonNode jsonNode;
    PR_List_Flow prListFlow;
    Page page;
    ILogin iLogin;
    ILogout iLogout;
    private String appUrl;

//TODO Constructor
    private PorCreate(){
    }

    private int status=0;

    public PorCreate(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout, PR_List_Flow prListFlow){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.prListFlow = prListFlow;
        this.logger = LoggerUtil.getLogger(PorCreate.class);
        this.appUrl = jsonNode.get("configSettings").get("appUrl").asText();
    }

    public void porCreateButtonForCatalog(String type, String purchaseType) {
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator prNavigationBarLocator = page.locator(PR_NAVIGATION_BAR);
            prNavigationBarLocator.click();

            String title = getTransactionTitle(type, purchaseType);
            Locator titleLocator = page.locator(LPorCreate.getTitle(title));
            titleLocator.first().click();

            if(type.equalsIgnoreCase("Sales")){
                Locator sendToYQuoteButtonLocator = page.locator(SEND_TO_Y_QUOTE_BUTTON);
                sendToYQuoteButtonLocator.click();

                Locator yesButtonLocator = page.locator(YES_BUTTON);
                yesButtonLocator.click();

                iLogout.performLogout();

                String requesterMailId = jsonNode.get("mailIds").get("requesterEmail").asText();
                iLogin.performLogin(requesterMailId);

                prListFlow.prListFlow();

                Locator convertSmToOmButtonLocator = page.locator(CONVERT_SM_TO_OM_BUTTON);
                convertSmToOmButtonLocator.click();

                Locator departmentPicDropDownLocator = page.locator(DEPARTMENT_PIC_DROP_DOWN);
                departmentPicDropDownLocator.click();

                Locator departmentPicSearchFieldLocator = page.locator(DEPARTMENT_PIC_SEARCH_FIELD);
                departmentPicSearchFieldLocator.fill(requesterMailId);

                Locator departmentPicMailIdLocator = page.locator(getDepartmentPic(requesterMailId));
                departmentPicMailIdLocator.first().click();

                Locator updateButtonLocator = page.locator(LPorCreate.UPDATE_BUTTON);
                updateButtonLocator.click();

                Locator quantityMismatchPopup = page.locator(QUANTITY_MISMATCH_POPUP);
                if(quantityMismatchPopup.isEnabled() || quantityMismatchPopup.isVisible()) {
                    yesButtonLocator.click();

                    Locator editButtonLocator = page.locator(LPrEdit.EDIT_BUTTON);
                    editButtonLocator.click();

                    Locator prUpdateButtonLocator = page.locator(LPrEdit.UPDATE_BUTTON);
                    prUpdateButtonLocator.click();

                    Locator submitButtonLocator = page.locator(LPorCreate.SUBMIT_BUTTON);
                    submitButtonLocator.click();

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);
                }
            }

            Locator porCreateButtonLocator = page.locator(POR_CREATE_BUTTON);
            porCreateButtonLocator.first().click();

            boolean advancePaymentFlag = jsonNode.get("purchaseOrderRequests").get("advancePaymentFlag").asBoolean();
            boolean milestonePaymentFlag = jsonNode.get("purchaseOrderRequests").get("milestonePaymentFlag").asBoolean();

            if(advancePaymentFlag || milestonePaymentFlag){
                advanceAndMilestonePayments(advancePaymentFlag, milestonePaymentFlag);
            }


        } catch (Exception exception) {
            logger.error("Exception in POR Create For Catalog Type Function: {}", exception.getMessage());
        }
    }

    public void porCreateButtonForNonCatalog(String type) {
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

                iLogout.performLogout();

                String requesterMailId = jsonNode.get("mailIds").get("requesterEmail").asText();
                iLogin.performLogin(requesterMailId);

                prListFlow.prListFlow();

                Locator convertSmToOmButtonLocator = page.locator(CONVERT_SM_TO_OM_BUTTON);
                convertSmToOmButtonLocator.click();

                Locator departmentPicDropDownLocator = page.locator(DEPARTMENT_PIC_DROP_DOWN);
                departmentPicDropDownLocator.click();

                Locator departmentPicSearchFieldLocator = page.locator(DEPARTMENT_PIC_SEARCH_FIELD);
                departmentPicSearchFieldLocator.fill(requesterMailId);

                Locator departmentPicMailIdLocator = page.locator(getDepartmentPic(requesterMailId));
                departmentPicMailIdLocator.first().click();

                Locator updateButtonLocator = page.locator(LPorCreate.UPDATE_BUTTON);
                updateButtonLocator.click();

                Locator quantityMismatchPopup = page.locator(QUANTITY_MISMATCH_POPUP);
                if(quantityMismatchPopup.isEnabled() || quantityMismatchPopup.isVisible()) {
                    yesButtonLocator.click();

                    Locator requoteButtonLocator = page.locator(LQuoRequote.REQUOTE_BUTTON);
                    requoteButtonLocator.click();

                    Locator submitButtonLocator = page.locator(LPorCreate.SUBMIT_BUTTON);
                    submitButtonLocator.click();

                    iLogout.performLogout();

                    String vendorMailId = jsonNode.get("mailIds").get("vendorEmail").asText();
                    iLogin.performLogin(vendorMailId);

                    Locator getTitleLocator = page.locator(getTitle(title));
                    getTitleLocator.first().click();

                    Locator requoteEditButtonLocator = page.locator(REQUOTE_EDIT_BUTTON);
                    requoteEditButtonLocator.click();

                    Locator updateButtonLocator1 = page.locator(LPorCreate.UPDATE_BUTTON);
                    updateButtonLocator1.click();

                    Locator acceptLocator = page.locator(ACCEPT_REMARKS_POP_UP);
                    acceptLocator.click();

                    iLogout.performLogout();

                    iLogin.performLogin(requesterMailId);

                    Locator rfqNavigationBarLocator1 = page.locator(RFQ_NAVIGATION_BAR);
                    rfqNavigationBarLocator1.click();

                    String title1 = getRFQTransactionTitle(type);
                    Locator titleLocator1 = page.locator(LPorCreate.getTitle(title1));
                    titleLocator1.first().click();

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);

                    Locator rfqNavigationBarLocator2 = page.locator(RFQ_NAVIGATION_BAR);
                    rfqNavigationBarLocator2.click();

                    String title2 = getRFQTransactionTitle(type);
                    Locator titleLocator2 = page.locator(LPorCreate.getTitle(title2));
                    titleLocator2.first().click();
                }
            }

            Locator porCreateButtonLocator = page.locator(POR_CREATE_BUTTON);
            porCreateButtonLocator.first().click();

            boolean advancePaymentFlag = jsonNode.get("purchaseOrderRequests").get("advancePaymentFlag").asBoolean();
            boolean milestonePaymentFlag = jsonNode.get("purchaseOrderRequests").get("milestonePaymentFlag").asBoolean();

            if(advancePaymentFlag || milestonePaymentFlag){
                advanceAndMilestonePayments(advancePaymentFlag, milestonePaymentFlag);
            }

        } catch (Exception exception) {
            logger.error("Exception in POR Create For Non-Catalog Type Function: {}", exception.getMessage());
        }
    }

    public void advanceAndMilestonePayments(boolean advancePaymentFlag, boolean milestonePaymentFlag) {
        try {
            String advancePaymentPercentage = jsonNode.get("purchaseOrderRequests").get("advancePaymentPercentage").asText();
            String creditPeriodInDays = jsonNode.get("purchaseOrderRequests").get("creditPeriodInDays").asText();

            if(advancePaymentFlag){
                //TODO Advance Payment
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

                    Locator milestonePaymentNameLocator = page.locator(MILESTONE_PAYMENT_NAME);
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
                //TODO Milestone Payment
                int milestoneCount = jsonNode.get("purchaseOrderRequests").get("milestonePaymentCount").asInt();
                int reminder = 100 % milestoneCount;
                int percentage = 100 / milestoneCount;

                for(int i = 1; i <= milestoneCount; i++){
                    Locator milestoneButtonLocator = page.locator(MILESTONE_PAYMENT_BUTTON);
                    milestoneButtonLocator.click();
                    Locator amilestonePaymentNameLocator = page.locator(MILESTONE_PAYMENT_NAME);
                    amilestonePaymentNameLocator.fill("Milestone-" + i);

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

    public void createButton(String type){
        try {
            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES);

            String reqType = type.equalsIgnoreCase("PS") ? "/api/PurchaseOrderRequests/" : "/api/PurchaseOrderRequestsSales/";

            Response createResponse = page.waitForResponse(
                    response -> response.url().startsWith(appUrl + reqType) && response.status() == 200,
                    yesButtonLocator::click
            );
            status = createResponse.status();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Create Function: {}", exception.getMessage());
        }
    }

    public int porCreate(String type, String purchaseType) {
        try {
            if (purchaseType.equalsIgnoreCase("Catalog")) {
                porCreateButtonForCatalog(type, purchaseType);
            } else if (purchaseType.equalsIgnoreCase("NonCatalog")) {
                porCreateButtonForNonCatalog(type);
                justification();
            }
            taxCode();
            porNotes();
            createButton(type);
        }catch (Exception exception) {
            logger.error("Exception in POR Create Function: {}", exception.getMessage());
        }
        return status;
    }

}
