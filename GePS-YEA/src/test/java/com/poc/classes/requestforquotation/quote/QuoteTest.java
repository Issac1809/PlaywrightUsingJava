package com.poc.classes.requestforquotation.quote;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class QuoteTest extends BaseTest {

    @Test
    public void quote() {
        try {
            iQuoSubmit.inviteRegisteredVendor();
            iQuoSubmit.vendorLogin();
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