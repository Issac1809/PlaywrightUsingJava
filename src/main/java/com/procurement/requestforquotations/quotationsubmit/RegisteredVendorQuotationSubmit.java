package com.procurement.requestforquotations.quotationsubmit;
import com.interfaces.QuotationSubmit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.LoginPageInterface;
import com.interfaces.LogoutPageInterface;
import java.nio.file.Paths;
import java.util.Properties;

public class RegisteredVendorQuotationSubmit implements QuotationSubmit {

    Properties properties;
    Page page;
    LoginPageInterface loginPageInterface;
    LogoutPageInterface logoutPageInterface;

    private RegisteredVendorQuotationSubmit(){
    }

//TODO Constructor
    public RegisteredVendorQuotationSubmit(LoginPageInterface loginPageInterface, Properties properties, Page page, LogoutPageInterface logoutPageInterface){
        this.loginPageInterface = loginPageInterface;
        this.properties = properties;
        this.page = page;
        this.logoutPageInterface = logoutPageInterface;
    }

    public void InviteRegisteredVendor(){
        try {
        loginPageInterface.LoginMethod(properties.getProperty("Buyer"));
        page.waitForSelector("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.waitForSelector("#addRequestForQuotationVendors").click();
        page.waitForSelector("#select2-vendorId-container").click();
        String vendorId = properties.getProperty("Vendor");
        page.waitForSelector(".select2-search__field").fill(vendorId);
        page.locator("//li[contains(text(), '"+ vendorId +"')]").first().click();
        page.waitForSelector("#saveRequestForQuotationVendor").click();
        page.waitForSelector("#vendorSendMailBtnId").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void VendorLogin() throws InterruptedException {
        try {
        loginPageInterface.LoginMethod(properties.getProperty("VendorMailId"));
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '" + title + "')]").first().click();
        page.waitForSelector("#btnSendQuote").click();
        page.waitForSelector("#incotermLocation").fill(properties.getProperty("Incoterm"));
        page.waitForSelector("#quotationReferenceNumber").fill(properties.getProperty("QuotationReferenceNumber"));
        Thread.sleep(2000);
        Locator validityDate = page.locator("#dates");
        validityDate.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']");
        int getTodayDayNumber = Integer.parseInt(today.textContent());
        int getTomorrowDayNumber = getTodayDayNumber + 1;
        int nextDayAfterThirty = 31;
        if (getTodayDayNumber == 30) {
            Locator day = page.locator("//span[contains(text(), '" + nextDayAfterThirty + "')]");
            if (day.isVisible() || !day.isHidden()) {
                day.click();
            } else {
                page.locator(".flatpickr-day.nextMonthDay").first().click();
            }
        }
        if (getTodayDayNumber == 31) {
            page.locator(".flatpickr-day.nextMonthDay").first().click();
        }
        else {
            page.locator("//span[contains(text(), '" + getTomorrowDayNumber + "')]").last().click();
        }
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void LiquidatedDamages(){
        try {
        page.waitForSelector("#liquidatedComplyId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RoHSCompliance(){
        try {
        page.waitForSelector("#rohsComplyId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void WarrantyRequirements(){
        try {
        page.waitForSelector("#warrantyRequirementsComplyId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationItems(){
        try {
        String hsCode = Integer.toString(Integer.parseInt(properties.getProperty("HSCode")));
        page.waitForSelector("#hsCode-1").fill(hsCode);
        page.waitForSelector("#make-1").fill(properties.getProperty("Make"));
        page.waitForSelector("#model-1").fill(properties.getProperty("Model"));
        page.waitForSelector("#partNumber-1").fill(properties.getProperty("PartNumber"));
        page.waitForSelector("#countryOfOrigin-1").fill(properties.getProperty("CountryOfOrigin"));
        String rate = Integer.toString(Integer.parseInt(properties.getProperty("Rate")));
        Locator getRateValue = page.locator("#rate-1");
        getRateValue.clear();
        getRateValue.fill(rate);
        String discount = Integer.toString(Integer.parseInt(properties.getProperty("Discount")));
        Locator getDiscount = page.locator("#discount-1");
        getDiscount.clear();
        getDiscount.fill(discount);
        String leadTime = Integer.toString(Integer.parseInt(properties.getProperty("LeadTime")));
        page.waitForSelector("#leadTime-1").fill(leadTime);
        page.waitForSelector("#notes-1").fill(properties.getProperty("QuotationNotes"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Gst(){
        try {
        String gst = Integer.toString(Integer.parseInt(properties.getProperty("Discount")));
        page.waitForSelector("#gstId").fill(gst);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationAttachments() {
        try {
//TODO Technical Attachment
        page.waitForSelector("#attachFile").click();
        Locator TechnicalFile = page.locator("#formFilePreupload");
        TechnicalFile.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Technical Documents.xlsx"));
        page.waitForSelector("#select2-attachmentTypeId-container").click();
        page.waitForSelector("//li[contains(text(), 'Technical')]").click();
        page.waitForSelector("#attachmentSaveId").click();
//TODO Commercial Attachment
        page.waitForSelector("#attachFile").click();
        Locator CommercialFile = page.locator("#formFilePreupload");
        CommercialFile.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Commercial Documents.xlsx"));
        page.waitForSelector("#select2-attachmentTypeId-container").click();
        page.waitForSelector("//li[contains(text(), 'Commercial')]").click();
        page.waitForSelector("#attachmentSaveId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationSubmitButton(){
        try {
        page.waitForSelector("#btnCreate").click();
        page.waitForSelector(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}