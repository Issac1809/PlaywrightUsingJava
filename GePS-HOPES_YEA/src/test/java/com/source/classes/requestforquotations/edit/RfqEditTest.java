package com.source.classes.requestforquotations.edit;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqEditTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void edit(String type) {
        try {
            iRfqEdit.rfqEditMethod(type);
        } catch (Exception exception) {
            logger.error("Exception in RFQ Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in RFQ Edit Test Function: " + exception.getMessage());
        }
    }
}