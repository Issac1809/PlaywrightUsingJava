package com.yokogawa.requestforquotations.quotationsubmit;
import com.microsoft.playwright.Page;
public interface QuotationSubmit {
    void InviteRegisteredVendor(String vendor, Page page);
    void VendorLogin(String vendorMailId, String incortermLocation, String quotationReferenceNumber, Page page) throws InterruptedException;
    void LiquidatedDamages(Page page);
    void RoHSCompliance(Page page);
    void WarrantyRequirements(Page page);
    void QuotationItems(int HSCode, String Make, String Model, String PartNumber, String CountryOfOrigin, int Rate, int Discount, int LeadTime, String QuotationNotes, Page page);
    void Gst(int GST, Page page);
    void QuotationAttachments(Page page);
    void QuotationSubmitButton(Page page);
}
