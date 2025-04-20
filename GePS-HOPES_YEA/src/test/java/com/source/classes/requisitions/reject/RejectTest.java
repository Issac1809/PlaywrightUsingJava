package com.source.classes.requisitions.reject;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RejectTest extends BaseTest {

    @Test@Parameters({"type", "purchaseType"})
    public void reject(String type, String purchaseType) {
        try {
            int status = iPrReject.reject(type, purchaseType);
            iPrEdit.edit(type, purchaseType);
            iPrSendForApproval.sendForApproval(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Edit was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Requisition Reject Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Reject Test Function: " + exception.getMessage());
        }
    }
}