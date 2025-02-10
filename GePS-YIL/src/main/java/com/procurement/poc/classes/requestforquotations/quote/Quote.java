package com.procurement.poc.classes.requestforquotations.quote;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.ILogin;
import com.interfaces.ILogout;
import com.procurement.poc.interfaces.requestforquotation.IQuoSubmit;

import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static com.factory.PlaywrightFactory.statusAssertion;
import static com.procurement.poc.constants.requestforquotations.LQuoSubmit.*;

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
        String buyerMailId = properties.getProperty("buyerEmail");
        iLogin.performLogin(buyerMailId);

        Locator rfqNavigationBar = page.locator(RFQ_NAVIGATION_BAR.getLocator());
        waitForLocator(rfqNavigationBar);
        rfqNavigationBar.click();

        String title = properties.getProperty("currentTitle");
        Locator getTitleLocator = page.locator(getString(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        Locator inviteVendorButton = page.locator(INVITE_VENDORS.getLocator());
        waitForLocator(inviteVendorButton);
        inviteVendorButton.click();

        Locator vendorSearchFieldLocator = page.locator(VENDOR_SEARCH_FIELD.getLocator());
        waitForLocator(vendorSearchFieldLocator);
        vendorSearchFieldLocator.click();

        String vendorId = properties.getProperty("vendorName");
        Locator vendorSearchLocator = page.locator(VENDOR_SEARCH.getLocator());
        waitForLocator(vendorSearchLocator);
        vendorSearchLocator.fill(vendorId);

        Locator getVendorLocator = page.locator(getListOption(vendorId));
        waitForLocator(getVendorLocator);
        getVendorLocator.first().click();

        Locator inviteVendorButtonLocator= page.locator(INVITE_BUTTON.getLocator());
        waitForLocator(inviteVendorButtonLocator);
        inviteVendorButtonLocator.click();

        Locator vendorEmailPopUpLocator = page.locator(VENDOR_EMAIL_POP_UP.getLocator());
        waitForLocator(vendorEmailPopUpLocator);
        vendorEmailPopUpLocator.click();

        statusAssertion(page,page::reload,"rfq","Floated");

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationCreateAndSubmit(){
        vendorLogin();
        compliances();
        quotationItems();
        quotationAttachments();
        quotationSubmitButton();
    }

    public void compliances(){
        liquidatedDamages();
        rohsCompliance();
        warrantyRequirements();
        packingForwarding();
        freight();
        insurance();
        bankGuarantee();
    }

    public void vendorLogin() {
        try {
        String vendorEmailId = properties.getProperty("vendorEmail");
        iLogin.performLogin(vendorEmailId);

        String title = properties.getProperty("currentTitle");
        Locator getTitleLocator = page.locator(getString(title));
        waitForLocator(getTitleLocator);
        getTitleLocator.first().click();

        Locator sendQuoteButtonLocator = page.locator(SEND_QUOTE_BUTTON.getLocator());
        waitForLocator(sendQuoteButtonLocator);
        sendQuoteButtonLocator.click();

        String incoterm = properties.getProperty("incotermLocation");
        Locator incotermLocationLocator = page.locator(INCOTERM_LOCATION.getLocator());
        waitForLocator(incotermLocationLocator);
        incotermLocationLocator.fill(incoterm);

        String quotationReferenceNumber = (properties.getProperty("quotationReferenceNumber") + Date.from(Instant.now()));
        Locator quotationRefNumLocator = page.locator(QUOTATION_REFERENCE_NUMBER.getLocator());
        waitForLocator(quotationRefNumLocator);
        quotationRefNumLocator.fill(quotationReferenceNumber);

        Locator validityDateLocator = page.locator(VALIDITY_DATE.getLocator());
        waitForLocator(validityDateLocator);
        validityDateLocator.click();

        Locator todayLocator = page.locator(TODAY.getLocator());
        int todayDayNumber = Integer.parseInt(todayLocator.textContent());

        if(todayDayNumber >= 16)
        {
            Locator nextMonth = page.locator(NEXT_MONTH_BUTTON.getLocator());
            waitForLocator(nextMonth);
            nextMonth.click();
            Locator day20 = page.locator(DAY20.getLocator());
            waitForLocator(day20);
            day20.click();
        }
        else {
            Locator day20 = page.locator(DAY20.getLocator());
            waitForLocator(day20);
            day20.click();
        }
//        int tomorrowDayNumber = todayDayNumber + 1;
//        int nextDayAfterThirty = 31;
//        if (todayDayNumber == 27) {
//            Locator dayLocator = page.locator(getString(Integer.toString(nextDayAfterThirty))).last();
//            if (dayLocator.isVisible() || !dayLocator.isHidden()) {
//                waitForLocator(dayLocator);
//                dayLocator.click();
//            } else {
//                Locator nextMonthFirstDayLocator = page.locator(FIRST_DAY_OF_NEXT_MONTH.getLocator());
//                waitForLocator(nextMonthFirstDayLocator);
//                nextMonthFirstDayLocator.first().click();
//            }
//        }
//        if (todayDayNumber == 31) {
//            Locator nextMonthFirstDayLocator = page.locator(FIRST_DAY_OF_NEXT_MONTH.getLocator());
//            waitForLocator(nextMonthFirstDayLocator);
//            nextMonthFirstDayLocator.first().click();
//        }
//        else {
//            page.locator(getString(Integer.toString(tomorrowDayNumber))).last().click();
//        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationItems(){
        try {
        String hsCode = properties.getProperty("hsCode");
        String make = properties.getProperty("make");
        String model = properties.getProperty("model");
        String partNumber = properties.getProperty("partNumber");
        String countryOfOrigin = properties.getProperty("countryOfOrigin");
        String rate = properties.getProperty("rate");
        String cgst = properties.getProperty("cgst");
        String sgst = properties.getProperty("sgst");
        String igst = properties.getProperty("igst");
        String discount = properties.getProperty("discount");
        String leadTime = properties.getProperty("leadTime");
        String notes = properties.getProperty("quotationNotes");


        List<String> itemSerialNumbers = page.locator(RFQ_ITEM_LIST.getLocator()).allTextContents();
        for(int i = 1; i <= itemSerialNumbers.size(); i++){

            Locator hsCodeLocator = page.locator(HS_CODE.getLocator() + i);
            waitForLocator(hsCodeLocator);
            hsCodeLocator.fill(hsCode);

            Locator makeLocator = page.locator(MAKE.getLocator() + i);
            waitForLocator(makeLocator);
            makeLocator.fill(make + i);

            Locator modelLocator = page.locator(MODEL.getLocator() + i);
            waitForLocator(modelLocator);
            modelLocator.fill(model + i);

            Locator partNumberLocator = page.locator(PART_NUMBER.getLocator() + i);
            waitForLocator(partNumberLocator);
            partNumberLocator.fill(partNumber + i);

            Locator countryOfOriginLocator = page.locator(COUNTRY_OF_ORIGIN.getLocator() + i);
            waitForLocator(countryOfOriginLocator);
            countryOfOriginLocator.fill(countryOfOrigin);

            Locator rateLocator = page.locator(RATE.getLocator() + i);
            waitForLocator(rateLocator);
            rateLocator.clear();
            rateLocator.fill(rate);

            Locator igstLocator = page.locator(IGST.getLocator() + i);
            waitForLocator(igstLocator);
            igstLocator.clear();
            igstLocator.fill(igst);

//            Locator cgstLocator = page.locator(CGST.getLocator() + i);
//            waitForLocator(cgstLocator);
//            cgstLocator.clear();
//            cgstLocator.fill(cgst);
//
//            Locator sgstLocator = page.locator(SGST.getLocator() + i);
//            waitForLocator(sgstLocator);
//            sgstLocator.clear();
//            sgstLocator.fill(sgst);

            Locator dicountLocator = page.locator(DISCOUNT.getLocator() + i);
            waitForLocator(dicountLocator);
            dicountLocator.clear();
            dicountLocator.fill(discount);

            Locator leatTimeLocator = page.locator(LEAD_TIME.getLocator() + i);
            waitForLocator(leatTimeLocator);
            leatTimeLocator.fill(leadTime);

            Locator quotationNotesLocator = page.locator(QUOTATION_NOTES.getLocator() + i);
            waitForLocator(quotationNotesLocator);
            quotationNotesLocator.fill(notes + i);

            String xpath = "(//a[@id='viewitemspecification'])["+i+"]";
            Locator itemSpecificationsLocator = page.locator(xpath);
            waitForLocator(itemSpecificationsLocator);
            itemSpecificationsLocator.click();

            Locator complyAll = page.locator("#complyAll");
            if(complyAll!=null){
                waitForLocator(complyAll);
                complyAll.click();
            }
            page.locator("#itemSpecificationSave").click();
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void gst(){
        try {
        String gst = properties.getProperty("Discount");
        Locator gstLocator = page.locator(GST.getLocator());
        waitForLocator(gstLocator);
        gstLocator.fill(gst);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationAttachments() {
        try {
        uploadAttachments("Downloads/Technical Document.xlsx", "Technical");
        uploadAttachments("Downloads/Commercial Document.xlsx", "Commercial");
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    private void uploadAttachments(String filePath, String attachmentType) {
        try {
        Locator attachFileLocator = page.locator(ATTACH_FILE.getLocator());
        waitForLocator(attachFileLocator);
        attachFileLocator.click();

        Locator fileInputLocator = page.locator(FILE_INPUT.getLocator());
        waitForLocator(fileInputLocator);
        fileInputLocator.setInputFiles(Paths.get(filePath));

        Locator attachmentTypeDropDownLocator = page.locator(ATTACHMENT_TYPE_DROPDOWN.getLocator());
        waitForLocator(attachmentTypeDropDownLocator);
        attachmentTypeDropDownLocator.click();

        Locator attachmentTypeLocator = page.locator(getListOption(attachmentType));
        waitForLocator(attachmentTypeLocator);
        attachmentTypeLocator.click();

        Locator saveAttachemnt = page.locator(SAVE_ATTACHMENTS.getLocator());
        waitForLocator(saveAttachemnt);
        saveAttachemnt.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void quotationSubmitButton(){
        try {
        Locator createButtonLocator = page.locator(CREATE_BUTTON.getLocator());
        waitForLocator(createButtonLocator);
        createButtonLocator.click();

        Locator acceptLocator = page.locator(ACCEPT_BUTTON_LOCATOR.getLocator());
        waitForLocator(acceptLocator);
        acceptLocator.click();

        iLogout.performLogout();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }


    public void liquidatedDamages(){
        try {
            Locator liquidatedDamagesLocator = page.locator(LIQUIDATED_DAMAGES.getLocator());
            waitForLocator(liquidatedDamagesLocator);
            liquidatedDamagesLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
    public void rohsCompliance(){
        try {
            Locator rohsCompliance = page.locator(ROHS_COMPLIANCE.getLocator());
            waitForLocator(rohsCompliance);
            rohsCompliance.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
    public void packingForwarding(){
        try {
            Locator packingForwarding = page.locator(PACKING_FORWARDING.getLocator());
            waitForLocator(packingForwarding);
            packingForwarding.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
    public void freight(){
        try {
            Locator freight = page.locator(FREIGHT.getLocator());
            waitForLocator(freight);
            freight.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
    public void insurance(){
        try {
            Locator insurance = page.locator(INSURANCE.getLocator());
            waitForLocator(insurance);
            insurance.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
    public void bankGuarantee(){
        try {
            Locator bankGuarantee = page.locator(BANK_GUARANTEE.getLocator());
            waitForLocator(bankGuarantee);
            bankGuarantee.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
    public void warrantyRequirements(){
        try {
            Locator warrantyRequirementsLocator = page.locator(WARRANTY_REQUIREMENTS.getLocator());
            waitForLocator(warrantyRequirementsLocator);
            warrantyRequirementsLocator.click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}