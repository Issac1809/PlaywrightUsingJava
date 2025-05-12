package com.source.classes.invoices.woinvoice.approve;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvApprovalTest extends BaseTest {

    @Epic("Work Order Approve")
    @Feature("Work Order Invoice Approve")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Approvers Can Able To Approve The Work Order Invoice")
    @Test(description = "Work Order Invoice Approve Test")
    public void approval(){
        try {
            iWoInvApproval.approval();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Approval Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Approval Test Function: " + exception.getMessage());

        }
    }
}