package com.source.classes.requestforquotations.quote;
import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.source.interfaces.login.ILogin;
import com.source.interfaces.logout.ILogout;
import com.source.interfaces.requestforquotation.IQuoSubmit;
import com.utils.LoggerUtil;
import org.apache.logging.log4j.Logger;
import java.nio.file.Paths;
import java.util.List;
import static com.constants.requestforquotations.LQuoSubmit.*;
import static com.constants.requisitions.LPrEdit.getTitle;
import static com.utils.GetTitleUtil.getRFQTransactionTitle;

public class Quote implements IQuoSubmit {

    Logger logger;
    JsonNode jsonNode;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private Quote(){
    }

//TODO Constructor
    public Quote(ILogin iLogin, JsonNode jsonNode, Page page, ILogout iLogout){
        this.logger = LoggerUtil.getLogger(Quote.class);
        this.iLogin = iLogin;
        this.jsonNode = jsonNode;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void inviteRegisteredVendor(String type){
        try {
            String buyerMailId = jsonNode.get("mailIds").get("buyerEmail").asText();
            iLogin.performLogin(buyerMailId);

            Locator rfqNavigationBar = page.locator(RFQ_NAVIGATION_BAR);
            rfqNavigationBar.click();

            String title = getRFQTransactionTitle(type);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator inviteVendorButton = page.locator(INVITE_VENDORS);
            inviteVendorButton.click();

            Locator vendorSearchFieldLocator = page.locator(VENDOR_SEARCH_FIELD);
            vendorSearchFieldLocator.click();

            String vendorId = jsonNode.get("mailIds").get("vendorEmail").asText();
            Locator vendorSearchLocator = page.locator(VENDOR_SEARCH);
            vendorSearchLocator.fill(vendorId);

            Locator getVendorLocator = page.locator(getVendor(vendorId));
            getVendorLocator.first().click();

            Locator inviteVendorButtonLocator= page.locator(INVITE_BUTTON);
            inviteVendorButtonLocator.click();

            Locator vendorEmailPopUpLocator = page.locator(VENDOR_EMAIL_POP_UP);
            vendorEmailPopUpLocator.click();

        iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Invite Registered Vendor Function: {}", exception.getMessage());
        }
    }

    public void vendorLogin(String type) {
        try {
            String vendorEmailId = jsonNode.get("mailIds").get("vendorEmail").asText();
            iLogin.performLogin(vendorEmailId);

            String title = getRFQTransactionTitle(type);
            Locator titleLocator = page.locator(getTitle(title));
            titleLocator.first().click();

            Locator sendQuoteButtonLocator = page.locator(SEND_QUOTE_BUTTON);
            sendQuoteButtonLocator.click();

            String incoterm = jsonNode.get("requestForQuotation").get("incotermLocation").asText();
            Locator incotermLocationLocator = page.locator(INCOTERM_LOCATION);
            incotermLocationLocator.fill(incoterm);

            String quotationReferenceNumber = jsonNode.get("requestForQuotation").get("quotationReferenceNumber").asText();
            Locator quotationRefNumLocator = page.locator(QUOTATION_REFERENCE_NUMBER);
            quotationRefNumLocator.fill(quotationReferenceNumber);

            Locator validityDateLocator = page.locator(VALIDITY_DATE);
            validityDateLocator.click();

            Locator todayLocator = page.locator(TODAY);
            int todayDayNumber = Integer.parseInt(todayLocator.textContent());
            int tomorrowDayNumber = todayDayNumber + 1;
            int nextDayAfterThirty = 31;

            if (todayDayNumber == 30) {
                Locator dayLocator = page.locator(getThirtyOne(nextDayAfterThirty));
                if (dayLocator.isVisible() || !dayLocator.isHidden()) {
                    dayLocator.click();
                } else {
                    Locator nextMonthFirstDayLocator = page.locator(FIRST_DAY_OF_NEXT_MONTH);
                    nextMonthFirstDayLocator.first().click();
                }
            }
            if (todayDayNumber == 31) {
                Locator nextMonthFirstDayLocator = page.locator(FIRST_DAY_OF_NEXT_MONTH);
                nextMonthFirstDayLocator.first().click();
            } else {
                page.locator(getNextDay(tomorrowDayNumber)).last().click();
            }
        } catch (Exception exception) {
            logger.error("Exception in Vendor Login Function: {}", exception.getMessage());
        }
    }

    public void liquidatedDamages(){
        try {
            Locator liquidatedDamagesLocator = page.locator(LIQUIDATED_DAMAGES);
            liquidatedDamagesLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in Liquidated Damages Function: {}", exception.getMessage());
        }
    }

    public void rohsCompliance(){
        try {
            Locator rohsCompliance = page.locator(ROHS_COMPLIANCE);
            rohsCompliance.click();
        } catch (Exception exception) {
            logger.error("Exception in RoHS Compliance Function: {}", exception.getMessage());
        }
    }

    public void warrantyRequirements(){
        try {
            Locator warrantyRequirementsLocator = page.locator(WARRANTY_REQUIREMENTS);
            warrantyRequirementsLocator.click();
        } catch (Exception exception) {
            logger.error("Exception in Warranty Requirements Function: {}", exception.getMessage());
        }
    }

    public void quotationItems(){
        try {
        String hsCode = jsonNode.get("requestForQuotation").get("hsCode").asText();
        String make = jsonNode.get("requestForQuotation").get("makeDescription").asText();
        String model = jsonNode.get("requestForQuotation").get("modelDescription").asText();
        String partNumber = jsonNode.get("requestForQuotation").get("partNumber").asText();
        String countryOfOrigin = jsonNode.get("requestForQuotation").get("countryOfOrigin").asText();
        String rate = jsonNode.get("requestForQuotation").get("rate").asText();
        String discount = jsonNode.get("requestForQuotation").get("discount").asText();
        String leadTime = jsonNode.get("requestForQuotation").get("leadTimeDays").asText();
        String notes = jsonNode.get("requestForQuotation").get("quotationNotes").asText();

        List<String> itemSerialNumbers = page.locator(RFQ_ITEM_LIST).allTextContents();
        for(int i = 0; i < itemSerialNumbers.size(); i++){

            Locator hsCodeLocator = page.locator(HS_CODE + i);
            hsCodeLocator.fill(hsCode);

            Locator makeLocator = page.locator(MAKE + i);
            makeLocator.fill(make + i);

            Locator modelLocator = page.locator(MODEL + i);
            modelLocator.fill(model + i);

            Locator partNumberLocator = page.locator(PART_NUMBER + i);
            partNumberLocator.fill(partNumber + i);

            Locator countryOfOriginLocator = page.locator(COUNTRY_OF_ORIGIN + i);
            countryOfOriginLocator.fill(countryOfOrigin + i);

            Locator rateLocator = page.locator(RATE + i);
            rateLocator.clear();
            rateLocator.fill(rate);

            Locator dicountLocator = page.locator(DISCOUNT + i);
            dicountLocator.clear();
            dicountLocator.fill(discount);

            Locator leatTimeLocator = page.locator(LEAD_TIME + i);
            leatTimeLocator.fill(leadTime);

            Locator quotationNotesLocator = page.locator(QUOTATION_NOTES + i);
            quotationNotesLocator.fill(notes + i);
        }
        } catch (Exception exception) {
            logger.error("Exception in Quotation Items Function: {}", exception.getMessage());
        }
    }

    public void gst(){
        try {
            String gst = jsonNode.get("mailIds").get("gstPercentage").asText();
            Locator gstLocator = page.locator(GST);
            gstLocator.fill(gst);
        } catch (Exception error) {
            logger.error("Exception in GST Function: {}", error.getMessage());
        }
    }

    public void quotationAttachments() {
        try {
            String technicalAttachmentFilePath = jsonNode.get("requestForQuotation").get("technicalAttachmentFilePath").asText();
            String commercialAttachmentFilePath = jsonNode.get("requestForQuotation").get("commercialAttachmentFilePath").asText();

            uploadAttachments(technicalAttachmentFilePath, "Technical");
            uploadAttachments(commercialAttachmentFilePath, "Commercial");
        } catch (Exception exception) {
            logger.error("Exception in Quotation Attachments Function: {}", exception.getMessage());
        }
    }

    private void uploadAttachments(String filePath, String attachmentType) {
        try {
            Locator attachFileLocator = page.locator(ATTACH_FILE);
            attachFileLocator.click();

            Locator fileInputLocator = page.locator(FILE_INPUT);
            fileInputLocator.setInputFiles(Paths.get(filePath));

            Locator attachmentTypeDropDownLocator = page.locator(ATTACHMENT_TYPE_DROPDOWN);
            attachmentTypeDropDownLocator.click();

            Locator attachmentTypeLocator = page.locator(getAttatmentType(attachmentType));
            attachmentTypeLocator.click();

            Locator saveAttachemnt = page.locator(SAVE_ATTACHMENTS);
            saveAttachemnt.click();
        } catch (Exception exception) {
            logger.error("Exception in Upload Attachments Function: {}", exception.getMessage());
        }
    }

    public void quotationSubmitButton(){
        try {
            Locator createButtonLocator = page.locator(CREATE_BUTTON);
            createButtonLocator.click();

            Locator acceptLocator = page.locator(ACCEPT_BUTTON_LOCATOR);
            acceptLocator.click();

            iLogout.performLogout();
        } catch (Exception exception) {
            logger.error("Exception in Quotation Submit Button Function: {}", exception.getMessage());
        }
    }
}