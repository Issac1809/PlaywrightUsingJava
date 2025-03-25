package com.source.classes.requestforquotation.suspend;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqSuspendPrEditTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void RfqSuspendPrEdit() {
        try {
            String type = "";
            String purchaseType = "";
            iRfqSuspend.suspendPREdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in RFQ Suspend PR Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in RFQ Suspend PR Edit Test Function: " + exception.getMessage());
        }
    }
}