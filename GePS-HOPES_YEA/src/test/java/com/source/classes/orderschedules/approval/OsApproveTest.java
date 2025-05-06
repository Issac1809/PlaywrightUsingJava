package com.source.classes.orderschedules.approval;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OsApproveTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void approve(String type, String purchaseType) {
        try {
            iOsApprove.approve(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in OS Approve Test Function: {}", exception.getMessage());
            Assert.fail("Exception in OS Approve Test Function: " + exception.getMessage());
        }
    }
}