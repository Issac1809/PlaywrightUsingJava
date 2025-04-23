package com.source.classes.requestforquotations.suspend;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqSuspendRfqEditTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void RfqSuspendRfqEditTestMethod(String type) {
        try {
            int status = iRfqSuspend.suspendRfqEdit(type);
            Assert.assertEquals(200, status, "Requisition Approve was not successful");
        } catch (Exception exception) {
            logger.error("Exception in RFQ Suspend and Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in RFQ Suspend and Edit Test Function: " + exception.getMessage());
        }
    }
}