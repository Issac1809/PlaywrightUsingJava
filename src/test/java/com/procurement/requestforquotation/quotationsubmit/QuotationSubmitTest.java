package com.procurement.requestforquotation.quotationsubmit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class QuotationSubmitTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void QuotationSubmitMethod() throws InterruptedException{
        try {
        quotationSubmit.InviteRegisteredVendor();
        quotationSubmit.VendorLogin();
        quotationSubmit.LiquidatedDamages();
        quotationSubmit.RoHSCompliance();
        quotationSubmit.WarrantyRequirements();
        quotationSubmit.QuotationItems();
        quotationSubmit.Gst();
        quotationSubmit.QuotationAttachments();
        quotationSubmit.QuotationSubmitButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}