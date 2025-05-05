package com.source.classes.purchaseorderrequests.suspend;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SuspendEditTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void suspend(String type, String purchaseType) {
        try {
            iPorSuspend.suspendPorEdit(type, purchaseType);
            iPorSuspend.suspendRfqOrPrEdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Suspend Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in POR Suspend Edit Test Function: " + exception.getMessage());
        }
    }
}