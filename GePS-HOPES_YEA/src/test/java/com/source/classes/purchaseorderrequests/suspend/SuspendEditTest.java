package com.source.classes.purchaseorderrequests.suspend;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SuspendEditTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void suspend() {
        try {
            String type = "";
            String purchaseType = "";

            iPorSuspend.suspendRfqOrPrEdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Suspend Edit Test Function: {}", exception.getMessage());
        }
    }
}