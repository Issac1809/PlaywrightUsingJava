package com.source.classes.invoices.poinvoice.sendforapproval;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvSendForApprovalTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Send For Approval")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Send The Purchase Order Invoice For Approval")
    @Test(description = "Purchase Order Invoice Send For Approval Test")
    public void sendForApproval(){
        try {
            iInvSendForApproval.sendForApproval();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Send For Approval Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Send For Approval Test Function: " + exception.getMessage());

        }
    }
}