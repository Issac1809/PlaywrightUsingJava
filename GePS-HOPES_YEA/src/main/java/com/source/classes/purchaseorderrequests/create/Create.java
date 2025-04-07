package com.source.classes.purchaseorderrequests.create;
import com.constants.purchaseorderrequests.LPorCreate;
import com.constants.requestforquotations.LCeCreate;
import com.constants.requestforquotations.LQuoRequote;
import com.constants.requisitions.LPrEdit;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.purchaseorderrequests.IPorCreate;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import static com.constants.purchaseorderrequests.LPorCreate.*;
import static com.constants.requestforquotations.LQuoRequote.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.*;

public class Create implements IPorCreate {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

//TODO Constructor
    private Create(){
    }

    public Create(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
        this.logger = LoggerUtil.getLogger(Create.class);
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

                //BW RPA Logic

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

                    //Convert SM To OM

                    iLogout.performLogout();

                    iLogin.performLogin(buyerMailId);
                }
            }

            Locator porCreateButtonLocator = page.locator(POR_CREATE_BUTTON);
            porCreateButtonLocator.first().click();
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

                //BW RPA Logic

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

                    //Convert SM to OM
                }
            }

        Locator porCreateButtonLocator = page.locator(POR_CREATE_BUTTON);
        porCreateButtonLocator.first().click();
        } catch (Exception exception) {
            logger.error("Exception in POR Create For Non-Catalog Type Function: {}", exception.getMessage());
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

    public void porCreate(){
        try {
            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator yesButtonLocator = page.locator(YES);
            yesButtonLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in POR Create Function: {}", exception.getMessage());
        }
    }
}