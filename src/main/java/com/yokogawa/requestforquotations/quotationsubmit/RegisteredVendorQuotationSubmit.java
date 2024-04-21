package com.yokogawa.requestforquotations.quotationsubmit;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.yokogawa.login.Login;
import com.yokogawa.login.LoginPage;
import com.yokogawa.logout.Logout;
import com.yokogawa.logout.LogoutPage;
import com.yokogawa.requestforquotations.quotationsubmit.QuotationSubmit;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static com.yokogawa.variables.VariablesForNonCatalog.NonCatalogTitle;
public class RegisteredVendorQuotationSubmit implements QuotationSubmit {
    Login login = new LoginPage();
    Logout logout = new LogoutPage();
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
        logout.Logout(page);
    }
    public void VendorLogin(String vendorMailId, String incortermLocation, String quotationReferenceNumber, Page page) {
        login.Login(vendorMailId, page);
        page.locator("//span[contains(text(), '" + NonCatalogTitle + "')]").first().click();
        page.locator("#btnSendQuote").click();
        page.locator("#incotermLocation").fill(incortermLocation);
        page.locator("#quotationReferenceNumber").fill(quotationReferenceNumber);
        Locator validityDate = page.locator("#dates");
        validityDate.click();
        Locator today = page.locator("//span[@class='flatpickr-day today']");
        int getTodayDayNumber = Integer.parseInt(today.textContent());
        int nextDay = 1;
        int nextDayAfterThirty = 31;
        int nextDayAfterThirtyOne = 32;
        if (getTodayDayNumber == 30) {
            int excludeDay1 = nextDay + getTodayDayNumber;
                Locator condition = page.locator("//span[contains(text(), '" + excludeDay1 + "')]");
            if (condition.isEnabled() && condition.isVisible()) {
                condition.first().click();
            } else {
                int excludeDay2 = nextDayAfterThirty - getTodayDayNumber;
                page.locator("//span[contains(text(), '"+ excludeDay2 +"')]").first().click();
            }
        }
        if (getTodayDayNumber == 31){
            int excludeDay3 = nextDayAfterThirtyOne - getTodayDayNumber;
            page.locator("//span[contains(text(), '"+ excludeDay3 +"')]").first().click();
        } else {
            int tomorrow = getTodayDayNumber + 1;
            page.locator("//span[contains(text(), '" + tomorrow + "')]").first().click();
        }
    }
    public void LiquidatedDamages(Page page){
        page.locator("#liquidatedComplyId").click();
    }
    public void RoHSCompliance(Page page){
        page.locator("#rohsComplyId").click();
    }
    public void WarrantyRequirements(Page page){
        page.locator("#warrantyRequirementsComplyId").click();
    }
    public void QuotationItems(int HSCode, String Make, String Model, String PartNumber, String CountryOfOrigin, int Rate, int Discount, int LeadTime, String QuotationNotes, Page page){
        String hsCode = Integer.toString(HSCode);
        page.locator("#hsCode-1").fill(hsCode);
        page.locator("#make-1").fill(Make);
        page.locator("#model-1").fill(Model);
        page.locator("#partNumber-1").fill(PartNumber);
        page.locator("#countryOfOrigin-1").fill(CountryOfOrigin);
        String rate = Integer.toString(Rate);
        Locator getRateValue = page.locator("#rate-1");
        getRateValue.clear();
        getRateValue.fill(rate);
        String discount = Integer.toString(Discount);
        Locator getDiscount = page.locator("#discount-1");
        getDiscount.clear();
        getDiscount.fill(discount);
        String leadTime = Integer.toString(Rate);
        page.locator("#leadTime-1").fill(leadTime);
        page.locator("#notes-1").fill(QuotationNotes);
    }
    public void Gst(int GST, Page page){
        String gst = Integer.toString(GST);
        page.locator("#gstId").fill(gst);
    }
    public void QuotationAttachments(Page page){
        //TODO Technical Attachment
        page.locator("#attachFile").click();
        Locator TechnicalFile = page.locator("#formFilePreupload");
        TechnicalFile.setInputFiles(Paths.get("./Downloads/Technical Documents.xlsx"));
        page.locator("#select2-attachmentTypeId-container").click();
        page.locator("//li[contains(text(), 'Technical')]").click();
        page.locator("#attachmentSaveId").click();
        //TODO Commercial Attachment
        page.locator("#attachFile").click();
        Locator CommercialFile = page.locator("#formFilePreupload");
        CommercialFile.setInputFiles(Paths.get("./Downloads/Commercial Documents.xlsx"));
        page.locator("#select2-attachmentTypeId-container").click();
        page.locator("//li[contains(text(), 'Commercial')]").click();
        page.locator("#attachmentSaveId").click();
    }
    public void QuotationSubmitButton(Page page){
        page.locator("#btnCreate").click();
        page.locator(".bootbox-accept").click();
        logout.Logout(page);
    }
}
