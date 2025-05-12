package com.source.classes.orderschedules.create;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OsCreateTest extends BaseTest {

    @Epic("Order Schedules")
    @Feature("Order Schedule Create")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Vendor Can Able To Create The Order Schedule")
    @Test(description = "Order Schedule Create Test")
    @Parameters({"type", "purchaseType"})
    public void create(String type, String purchaseType) {
        try {
            iOsCreate.create(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in OS Create Test Function: {}", exception.getMessage());
            Assert.fail("Exception in OS Create Test Function: " + exception.getMessage());
        }
    }
}