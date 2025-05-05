package com.source.classes.invoices.woinvoice.approve;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvApprovalTest extends BaseTest {

    @Test
    public void approval(){
        try {
            iWoInvApproval.approval();
        } catch (Exception exception) {
            logger.error("Exception in WO Invoice Approval Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Approval Test Function: " + exception.getMessage());

        }
    }
}