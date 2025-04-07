package com.source.classes.requisition.approve;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ApproveTest extends BaseTest {

    @Test
    @Parameters({"type","purchaseType"})
    public void approve(){
        try {
            String purchaseType = "";
            String type = "";
            int status = iPrApprove.approve(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Approve was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Requisition Approve Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Edit Test Function: " + exception.getMessage());
        }
    }
}