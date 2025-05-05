package com.source.classes.invoices.poinvoice.sendforapproval;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvSendForApprovalTest extends BaseTest {

    @Test
    public void sendForApproval(){
        try {
            iInvSendForApproval.sendForApproval();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Send For Approval Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Send For Approval Test Function: " + exception.getMessage());

        }
    }
}