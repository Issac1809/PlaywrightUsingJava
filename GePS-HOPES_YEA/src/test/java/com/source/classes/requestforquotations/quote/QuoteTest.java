package com.source.classes.requestforquotations.quote;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class QuoteTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void quote() {
        try {
            String type = "";
            iQuoSubmit.inviteRegisteredVendor(type);
            iQuoSubmit.vendorLogin(type);
            iQuoSubmit.liquidatedDamages();
            iQuoSubmit.rohsCompliance();
            iQuoSubmit.warrantyRequirements();
            iQuoSubmit.quotationItems();
            iQuoSubmit.gst();
            iQuoSubmit.quotationAttachments();
            iQuoSubmit.quotationSubmitButton(type);
        } catch (Exception exception) {
            logger.error("Exception in Quotation Submit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Quotation Submit Test Function: " + exception.getMessage());
        }
    }
}