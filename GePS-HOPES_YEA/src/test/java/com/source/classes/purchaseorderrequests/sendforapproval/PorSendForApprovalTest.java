package com.source.classes.purchaseorderrequests.sendforapproval;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorSendForApprovalTest extends BaseTest {

    @Epic("Purchase Order Requests")
    @Feature("Purchase Order Request Send For Approval")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Buyer Can Able To Send The Purchase Order Request For Approval")
    @Test(description = "Purchase Order Request Send For Approval Test")
    @Parameters({"type", "purchaseType"})
    public void sendForApproval(String type, String purchaseType) {
        try {
            iPorSendForApproval.sendForApproval(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Send For Approval Test function: {}", exception.getMessage());
            Assert.fail("Exception in POR Send For Approval Test Function: " + exception.getMessage());
        }
    }
}