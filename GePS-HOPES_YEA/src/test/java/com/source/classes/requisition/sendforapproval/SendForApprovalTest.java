package com.source.classes.requisition.sendforapproval;//package com.classes.requisition.sendforapproval;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SendForApprovalTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void sendForApproval() {
        try {
            String purchaseType = "";
            String type = "";
            int status = iPrSendForApproval.sendForApproval(type, purchaseType);

            Assert.assertEquals(200, status, "API Call was not successful");
        } catch (Exception exception) {
            logger.error("Error in Send For Approval Test Function: {}", exception.getMessage());
        }
    }
}