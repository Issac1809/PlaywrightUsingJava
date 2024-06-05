package com.yokogawa.requestforquotations.quotationsubmit;

public interface QuotationSubmit {
    void InviteRegisteredVendor();
    void VendorLogin() throws InterruptedException;
    void LiquidatedDamages();
    void RoHSCompliance();
    void WarrantyRequirements();
    void QuotationItems();
    void Gst();
    void QuotationAttachments();
    void QuotationSubmitButton();
}
