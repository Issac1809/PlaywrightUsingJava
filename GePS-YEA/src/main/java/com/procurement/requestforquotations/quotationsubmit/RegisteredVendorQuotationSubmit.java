package com.procurement.requestforquotations.quotationsubmit;
import com.interfaces.rfq.QuotationSubmit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.interfaces.login.LoginPageInterface;
import com.interfaces.logout.LogoutPageInterface;
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
        page.locator("//*[contains(text(), 'Request For Quotations')]").click();
        String title = properties.getProperty("Title");
        page.locator("//span[contains(text(), '"+ title +"')]").first().click();
        page.locator("#addRequestForQuotationVendors").click();
        page.locator("#select2-vendorId-container").click();
        String vendorId = properties.getProperty("Vendor");
        page.locator(".select2-search__field").fill(vendorId);
        page.locator("//li[contains(text(), '"+ vendorId +"')]").first().click();
        page.locator("#saveRequestForQuotationVendor").click();
        page.locator("#vendorSendMailBtnId").click();
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
        page.locator("#btnSendQuote").click();
        page.locator("#incotermLocation").fill(properties.getProperty("Incoterm"));
        page.locator("#quotationReferenceNumber").fill(properties.getProperty("QuotationReferenceNumber"));
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
        page.locator("#liquidatedComplyId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void RoHSCompliance(){
        try {
        page.locator("#rohsComplyId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void WarrantyRequirements(){
        try {
        page.locator("#warrantyRequirementsComplyId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationItems(){
        try {
        String hsCode = Integer.toString(Integer.parseInt(properties.getProperty("HSCode")));
        page.locator("#hsCode-1").fill(hsCode);
        page.locator("#make-1").fill(properties.getProperty("Make"));
        page.locator("#model-1").fill(properties.getProperty("Model"));
        page.locator("#partNumber-1").fill(properties.getProperty("PartNumber"));
        page.locator("#countryOfOrigin-1").fill(properties.getProperty("CountryOfOrigin"));
        String rate = Integer.toString(Integer.parseInt(properties.getProperty("Rate")));
        Locator getRateValue = page.locator("#rate-1");
        getRateValue.clear();
        getRateValue.fill(rate);
        String discount = Integer.toString(Integer.parseInt(properties.getProperty("Discount")));
        Locator getDiscount = page.locator("#discount-1");
        getDiscount.clear();
        getDiscount.fill(discount);
        String leadTime = Integer.toString(Integer.parseInt(properties.getProperty("LeadTime")));
        page.locator("#leadTime-1").fill(leadTime);
        page.locator("#notes-1").fill(properties.getProperty("QuotationNotes"));
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void Gst(){
        try {
        String gst = Integer.toString(Integer.parseInt(properties.getProperty("Discount")));
        page.locator("#gstId").fill(gst);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationAttachments() {
        try {
//TODO Technical Attachment
        page.locator("#attachFile").click();
        Locator TechnicalFile = page.locator("#formFilePreupload");
        TechnicalFile.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Technical Documents.xlsx"));
        page.locator("#select2-attachmentTypeId-container").click();
        page.locator("//li[contains(text(), 'Technical')]").click();
        page.locator("#attachmentSaveId").click();
//TODO Commercial Attachment
        page.locator("#attachFile").click();
        Locator CommercialFile = page.locator("#formFilePreupload");
        CommercialFile.setInputFiles(Paths.get("D://YokogawaAsiaPrivateLimited//Downloads//Commercial Documents.xlsx"));
        page.locator("#select2-attachmentTypeId-container").click();
        page.locator("//li[contains(text(), 'Commercial')]").click();
        page.locator("#attachmentSaveId").click();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }

    public void QuotationSubmitButton(){
        try {
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        logoutPageInterface.LogoutMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}