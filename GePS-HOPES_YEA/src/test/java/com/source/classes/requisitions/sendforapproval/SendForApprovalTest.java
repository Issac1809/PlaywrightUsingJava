package com.source.classes.requisitions.sendforapproval;//package com.classes.requisition.sendforapproval;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SendForApprovalTest extends BaseTest {

    @Test
    @Parameters({"type","purchaseType"})
    public void sendForApproval() {
        try {
            String purchaseType = "";
            String type = "";
            int status = iPrSendForApproval.sendForApproval(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Send For Approval was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Send For Approval Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Edit Test Function: " + exception.getMessage());
        }
    }
}