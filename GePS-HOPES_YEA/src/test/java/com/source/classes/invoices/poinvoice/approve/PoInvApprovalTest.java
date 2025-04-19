package com.source.classes.invoices.poinvoice.approve;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvApprovalTest extends BaseTest {

    @Test
    public void approval(){
        try {
            iInvApproval.approval();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Approval Test function: {}", exception.getMessage());
        }
    }
}