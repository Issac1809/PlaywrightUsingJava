package com.source.classes.requisitions.suspend;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BuyerSuspendTest extends BaseTest {

    @Epic("Requisitions")
    @Feature("Requisition Buyer Suspend")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Buyer Can Suspend The Requisition")
    @Test(description = "Requisition Buyer Suspend Test")
    @Parameters({"type","purchaseType"})
    public void suspend(String type, String purchaseType) {
        try {
            int status = iPrBuyerSuspend.suspend(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Suspend was not Successful");
            iPrEdit.edit(type, purchaseType);
            iPrSendForApproval.sendForApproval(type, purchaseType);
            iPrApprove.approve(type, purchaseType);
            iPrAssign.buyerManagerAssign(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Buyer Suspend Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Buyer Suspend Test Function: " + exception.getMessage());
        }
    }
}