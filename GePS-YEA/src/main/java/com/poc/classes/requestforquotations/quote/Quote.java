package com.poc.classes.requestforquotations.quote;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.poc.interfaces.login.ILogin;
import com.poc.interfaces.logout.ILogout;
import com.poc.interfaces.requestforquotation.IQuoSubmit;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import static com.constants.requestforquotations.LQuoSubmit.*;
import static com.factory.PlaywrightFactory.waitForLocator;

public class Quote implements IQuoSubmit {

    Properties properties;
    Page page;
    ILogin iLogin;
    ILogout iLogout;

    private Quote(){
    }

//TODO Constructor
    public Quote(ILogin iLogin, Properties properties, Page page, ILogout iLogout){
        this.iLogin = iLogin;
        this.properties = properties;
        this.page = page;
        this.iLogout = iLogout;
    }

    public void inviteRegisteredVendor(){
        try {
        String buyerMailId = properties.getProperty("Buyer");
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBar = page.locator(RFQ_NAVIGATION_BAR);
        waitForLocator(rfqNavigationBar);
        rfqNavigationBar.click();

        String title = properties.getProperty("Title");
        Locator getTitleLocator = page.locator(getTitle(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        Locator inviteVendorButton = page.locator(INVITE_VENDORS);
        waitForLocator(inviteVendorButton);
        inviteVendorButton.click();

        Locator vendorSearchFieldLocator = page.locator(VENDOR_SEARCH_FIELD);
        waitForLocator(vendorSearchFieldLocator);
        vendorSearchFieldLocator.click();

        String vendorId = properties.getProperty("Vendor");
        Locator vendorSearchLocator = page.locator(VENDOR_SEARCH);
        waitForLocator(vendorSearchLocator);
        vendorSearchLocator.fill(vendorId);

        Locator getVendorLocator = page.locator(getVendor(vendorId));
        waitForLocator(getVendorLocator);
        getVendorLocator.first().click();

        Locator inviteVendorButtonLocator= page.locator(INVITE_BUTTON);
        waitForLocator(inviteVendorButtonLocator);
        inviteVendorButtonLocator.click();

        Locator vendorEmailPopUpLocator = page.locator(VENDOR_EMAIL_POP_UP);
        waitForLocator(vendorEmailPopUpLocator);
        vendorEmailPopUpLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void vendorLogin() {
        try {
        String vendorEmailId = properties.getProperty("VendorMailId");
        iLogin.performLogin(vendorEmailId);

        String title = properties.getProperty("Title");
        Locator getTitleLocator = page.locator(getTitle(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        Locator sendQuoteButtonLocator = page.locator(SEND_QUOTE_BUTTON);
        waitForLocator(sendQuoteButtonLocator);
        sendQuoteButtonLocator.click();

        String incoterm = properties.getProperty("Incoterm");
        Locator incotermLocationLocator = page.locator(INCOTERM_LOCATION);
        waitForLocator(incotermLocationLocator);
        incotermLocationLocator.fill(incoterm);

        String quotationReferenceNumber = properties.getProperty("QuotationReferenceNumber");
        Locator quotationRefNumLocator = page.locator(QUOTATION_REFERENCE_NUMBER);
        waitForLocator(quotationRefNumLocator);
        quotationRefNumLocator.fill(quotationReferenceNumber);

        Locator validityDateLocator = page.locator(VALIDITY_DATE);
        waitForLocator(validityDateLocator);
        validityDateLocator.click();

        Locator todayLocator = page.locator(TODAY);
        int todayDayNumber = Integer.parseInt(todayLocator.textContent());
        int tomorrowDayNumber = todayDayNumber + 1;
        int nextDayAfterThirty = 31;
        if (todayDayNumber == 30) {
            Locator dayLocator = page.locator(getThirtyOne(nextDayAfterThirty));
            if (dayLocator.isVisible() || !dayLocator.isHidden()) {
                waitForLocator(dayLocator);
                dayLocator.click();
            } else {
                Locator nextMonthFirstDayLocator = page.locator(FIRST_DAY_OF_NEXT_MONTH);
                waitForLocator(nextMonthFirstDayLocator);
                nextMonthFirstDayLocator.first().click();
            }
        }
        if (todayDayNumber == 31) {
            Locator nextMonthFirstDayLocator = page.locator(FIRST_DAY_OF_NEXT_MONTH);
            waitForLocator(nextMonthFirstDayLocator);
            nextMonthFirstDayLocator.first().click();
        }
        else {
            page.locator(getNextDay(tomorrowDayNumber)).last().click();
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void liquidatedDamages(){
        try {
        Locator liquidatedDamagesLocator = page.locator(LIQUIDATED_DAMAGES);
        waitForLocator(liquidatedDamagesLocator);
        liquidatedDamagesLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void rohsCompliance(){
        try {
        Locator rohsCompliance = page.locator(ROHS_COMPLIANCE);
        waitForLocator(rohsCompliance);
        rohsCompliance.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void warrantyRequirements(){
        try {
        Locator warrantyRequirementsLocator = page.locator(WARRANTY_REQUIREMENTS);
        waitForLocator(warrantyRequirementsLocator);
        warrantyRequirementsLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationItems(){
        try {
        String hsCode = properties.getProperty("HSCode");
        String make = properties.getProperty("Make");
        String model = properties.getProperty("Model");
        String partNumber = properties.getProperty("PartNumber");
        String countryOfOrigin = properties.getProperty("CountryOfOrigin");
        String rate = properties.getProperty("Rate");
        String discount = properties.getProperty("Discount");
        String leadTime = properties.getProperty("LeadTime");
        String notes = properties.getProperty("QuotationNotes");

        List<String> itemSerialNumbers = page.locator(RFQ_ITEM_LIST).allTextContents();
        for(int i = 0; i < itemSerialNumbers.size(); i++){

            Locator hsCodeLocator = page.locator(HS_CODE + i);
            waitForLocator(hsCodeLocator);
            hsCodeLocator.fill(hsCode);

            Locator makeLocator = page.locator(MAKE + i);
            waitForLocator(makeLocator);
            makeLocator.fill(make + i);

            Locator modelLocator = page.locator(MODEL + i);
            waitForLocator(modelLocator);
            modelLocator.fill(model + i);

            Locator partNumberLocator = page.locator(PART_NUMBER + i);
            waitForLocator(partNumberLocator);
            partNumberLocator.fill(partNumber + i);

            Locator countryOfOriginLocator = page.locator(COUNTRY_OF_ORIGIN + i);
            waitForLocator(countryOfOriginLocator);
            countryOfOriginLocator.fill(countryOfOrigin + i);

            Locator rateLocator = page.locator(RATE + i);
            waitForLocator(rateLocator);
            rateLocator.clear();
            rateLocator.fill(rate);

            Locator dicountLocator = page.locator(DISCOUNT + i);
            waitForLocator(dicountLocator);
            dicountLocator.clear();
            dicountLocator.fill(discount);

            Locator leatTimeLocator = page.locator(LEAD_TIME + i);
            waitForLocator(leatTimeLocator);
            leatTimeLocator.fill(leadTime);

            Locator quotationNotesLocator = page.locator(QUOTATION_NOTES + i);
            waitForLocator(quotationNotesLocator);
            quotationNotesLocator.fill(notes + i);
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void gst(){
        try {
        String gst = properties.getProperty("Discount");
        Locator gstLocator = page.locator(GST);
        waitForLocator(gstLocator);
        gstLocator.fill(gst);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationAttachments() {
        try {
        uploadAttachments("C://Cormsquare//GePS-YEA//Downloads//Technical Documents.xlsx", "Technical");
        uploadAttachments("C://Cormsquare//GePS-YEA//Downloads//Commercial Documents.xlsx", "Commercial");
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    private void uploadAttachments(String filePath, String attachmentType) {
        try {
        Locator attachFileLocator = page.locator(ATTACH_FILE);
        waitForLocator(attachFileLocator);
        attachFileLocator.click();

        Locator fileInputLocator = page.locator(FILE_INPUT);
        waitForLocator(fileInputLocator);
        fileInputLocator.setInputFiles(Paths.get(filePath));

        Locator attachmentTypeDropDownLocator = page.locator(ATTACHMENT_TYPE_DROPDOWN);
        waitForLocator(attachmentTypeDropDownLocator);
        attachmentTypeDropDownLocator.click();

        Locator attachmentTypeLocator = page.locator(getAttatmentType(attachmentType));
        waitForLocator(attachmentTypeLocator);
        attachmentTypeLocator.click();

        Locator saveAttachemnt = page.locator(SAVE_ATTACHMENTS);
        waitForLocator(saveAttachemnt);
        saveAttachemnt.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationSubmitButton(){
        try {
        Locator createButtonLocator = page.locator(CREATE_BUTTON);
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_BUTTON_LOCATOR);
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}