package com.source.classes.orderschedules.approval;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OsApproveTest extends BaseTest {

    @Test
    public void approve() {
        try {
            iOsApprove.approve();
        } catch (Exception exception) {
            logger.error("Exception in OS Approve Test Function: {}", exception.getMessage());
            Assert.fail("Exception in OS Approve Test Function: " + exception.getMessage());
        }
    }
}