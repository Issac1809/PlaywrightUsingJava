package com.source.classes.orderschedules.approval;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OsApproveTest extends BaseTest {

    @Epic("Order Schedules")
    @Feature("Order Schedule Approve")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Approver Can Able To Approve The Order Schedule")
    @Test(description = "Order Schedule Approve Test")
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