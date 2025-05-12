package com.source.classes.orderschedules.reject;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OsRejectTest extends BaseTest {

    @Epic("Order Schedules")
    @Feature("Order Schedule Reject")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Approver Can Able To Reject The Order Schedule")
    @Test(description = "Order Schedule Reject Test")
    @Parameters({"type", "purchaseType"})
    public void reject(String type, String purchaseType) {
        try {
            iOsReject.reject(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in OS Reject Test Function: {}", exception.getMessage());
            Assert.fail("Exception in OS Reject Test Function: " + exception.getMessage());
        }
    }
}