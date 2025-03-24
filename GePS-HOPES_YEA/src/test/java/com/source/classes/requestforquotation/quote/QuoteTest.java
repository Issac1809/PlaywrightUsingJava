package com.source.classes.requestforquotation.quote;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class QuoteTest extends BaseTest {

    @Test
    public void quote() {
        try {
            iQuoSubmit.inviteRegisteredVendor(String type, String purchaseType);
            iQuoSubmit.vendorLogin(String type, String purchaseType);
            iQuoSubmit.liquidatedDamages();
            iQuoSubmit.rohsCompliance();
            iQuoSubmit.warrantyRequirements();
            iQuoSubmit.quotationItems();
            iQuoSubmit.gst();
            iQuoSubmit.quotationAttachments();
            iQuoSubmit.quotationSubmitButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}