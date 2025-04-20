package com.source.classes.requisitions.suspend;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BuyerManagerSuspendTest extends BaseTest {

    @Test
    @Parameters({"type","purchaseType"})
    public void suspend(String type, String purchaseType) {
        try {
            iPrBuyerManagerSuspend.suspend(type, purchaseType);
            iPrEdit.edit(type, purchaseType);
            iPrSendForApproval.sendForApproval(type, purchaseType);
            iPrApprove.approve(type, purchaseType);
            iPrAssign.buyerManagerAssign(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Buyer Manager Suspend Test Function: {}", exception.getMessage());
        }
    }
}
