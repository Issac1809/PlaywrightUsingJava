package com.source.classes.requestforquotations.suspend;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqSuspendPrEditTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void RfqSuspendPrEdit(String type, String purchaseType) {
        try {
            iRfqSuspend.suspendPREdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in RFQ Suspend PR Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in RFQ Suspend PR Edit Test Function: " + exception.getMessage());
        }
    }
}