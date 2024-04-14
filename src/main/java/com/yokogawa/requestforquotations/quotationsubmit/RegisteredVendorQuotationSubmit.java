package com.yokogawa.requestforquotations.quotationsubmit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.requestforquotations.quotationsubmit.QuotationSubmit;

import java.nio.file.Paths;
import java.time.LocalDate;
import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;
public class RegisteredVendorQuotationSubmit implements QuotationSubmit {
    Login login = new LoginPage();
    LocalDate currentDate = LocalDate.now(); // Get the current date
    int daysInCurrentMonth = currentDate.lengthOfMonth(); // Get the maximum number of days in the current month
    int tomorrowDayOfMonth = currentDate.getDayOfMonth() + 1; // Calculate the day for tomorrow
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    public void InviteRegisteredVendor(String vendor, Page page){
        page.locator("#addRequestForQuotationVendors").click();
        page.locator("#select2-vendorId-container").click();
        page.locator(".select2-search__field").fill(vendor);
        page.locator("//li[contains(text(), '"+ vendor +"')]").first().click();
        page.locator("#saveRequestForQuotationVendor").click();
        page.locator("#vendorSendMailBtnId").click();
    }
    public void VendorLogin(String vendorMailId, String incortermLocation, String quotationReferenceNumber, Page page1){
        login.Login(vendorMailId, page1);
        page1.locator("//span[contains(text(), '"+ NonCatalogTitle +"')]").first().click();
        page1.locator("#btnSendQuote").click();
        page1.locator("#incotermLocation").fill(incortermLocation);
        page1.locator("#quotationReferenceNumber").fill(quotationReferenceNumber);
        page1.locator("#dates").click();

        if (tomorrowDayOfMonth > daysInCurrentMonth) {
            currentDate = currentDate.plusMonths(1); // Move to the next month
            tomorrowDayOfMonth = 1; // Set tomorrow's day to the first day of the next month
            Locator tomorrowDate = page1.locator("//span[@class='flatpickr-day' and text()='" + tomorrowDayOfMonth + "']");
            tomorrowDate.click();
        } else {
            Locator tomorrowDate = page1.locator("//span[@class='flatpickr-day' and text()='" + tomorrow.getDayOfMonth() + "']");
            tomorrowDate.click();
        }
    }
    public void LiquidatedDamages(Page page1){
        page1.locator("#liquidatedComplyId").click();
    }
    public void RoHSCompliance(Page page1){
        page1.locator("#rohsComplyId").click();
    }
    public void WarrantyRequirements(Page page1){
        page1.locator("#warrantyRequirementsComplyId").click();
    }
    public void QuotationItems(int HSCode, String Make, String Model, String PartNumber, String CountryOfOrigin, int Rate, int Discount, int LeadTime, String QuotationNotes, Page page1){
        String hsCode = Integer.toString(HSCode);
        page1.locator("#hsCode-1").fill(hsCode);
        page1.locator("#make-1").fill(Make);
        page1.locator("#model-1").fill(Model);
        page1.locator("#partNumber-1").fill(PartNumber);
        page1.locator("#countryOfOrigin-1").fill(CountryOfOrigin);
        String rate = Integer.toString(Rate);
        Locator getRateValue = page1.locator("#rate-1");
        getRateValue.clear();
        getRateValue.fill(rate);
        String discount = Integer.toString(Discount);
        Locator getDiscount = page1.locator("#discount-1");
        getDiscount.clear();
        getDiscount.fill(discount);
        String leadTime = Integer.toString(Rate);
        page1.locator("#leadTime-1").fill(leadTime);
        page1.locator("#notes-1").fill(QuotationNotes);
    }
    public void Gst(int GST, Page page1){
        String gst = Integer.toString(GST);
        page1.locator("#gstId").fill(gst);
    }
    public void QuotationAttachments(Page page1){
        //TODO Technical Attachment
        page1.locator("#attachFile").click();
        Locator TechnicalFile = page1.locator("#formFilePreupload");
        TechnicalFile.click();
        TechnicalFile.setInputFiles(Paths.get("./Downloads/Technical Documents.xlsx"));
        page1.locator("#select2-attachmentTypeId-container").click();
        page1.locator("//li[contains(text(), 'Technical')]").click();
        page1.locator("#attachmentSaveId").click();
        //TODO Commercial Attachment
        page1.locator("#attachFile").click();
        Locator CommercialFile = page1.locator("#formFilePreupload");
        CommercialFile.click();
        CommercialFile.setInputFiles(Paths.get("./Downloads/Commercial Documents.xlsx"));
        page1.locator("#select2-attachmentTypeId-container").click();
        page1.locator("//li[contains(text(), 'Commercial')]").click();
        page1.locator("#attachmentSaveId").click();
    }
    public void QuotationSubmitButton(Page page1){
        page1.locator("#btnCreate").click();
        page1.locator(".bootbox-accept").click();
    }
}
