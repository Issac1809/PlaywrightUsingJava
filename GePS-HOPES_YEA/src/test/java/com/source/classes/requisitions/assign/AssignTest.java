package com.source.classes.requisitions.assign;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AssignTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void assign(String type, String purchaseType) {
        try {
            int status = iPrAssign.buyerManagerAssign(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Create was not Successful");
        } catch (Exception exception) {
            logger.error("Exception in Requisition Assign Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Assign Test Function: " + exception.getMessage());
        }
    }
}