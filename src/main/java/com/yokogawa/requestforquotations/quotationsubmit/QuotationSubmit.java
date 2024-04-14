package com.yokogawa.requestforquotations.quotationsubmit;
import com.microsoft.playwright.Page;
public interface QuotationSubmit {
    void InviteRegisteredVendor(String vendor, Page page);
    void VendorLogin(String vendorMailId, String incortermLocation, String quotationReferenceNumber, Page page1);
    void LiquidatedDamages(Page page1);
    void RoHSCompliance(Page page1);
    void WarrantyRequirements(Page page1);
    void QuotationItems(int HSCode, String Make, String Model, String PartNumber, String CountryOfOrigin, int Rate, int Discount, int LeadTime, String QuotationNotes, Page page1);
    void Gst(int GST, Page page1);
    void QuotationAttachments(Page page1);
    void QuotationSubmitButton(Page page1);
}
