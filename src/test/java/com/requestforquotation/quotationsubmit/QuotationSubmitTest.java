package com.requestforquotation.quotationsubmit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class QuotationSubmitTest extends BaseTest {

    @Test
    public void QuotationSubmitMethod() throws InterruptedException{
        quotationSubmit.InviteRegisteredVendor();
        quotationSubmit.VendorLogin();
        quotationSubmit.LiquidatedDamages();
        quotationSubmit.RoHSCompliance();
        quotationSubmit.WarrantyRequirements();
        quotationSubmit.QuotationItems();
        quotationSubmit.Gst();
        quotationSubmit.QuotationAttachments();
        quotationSubmit.QuotationSubmitButton();
    }
}