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
            iPrAssign.buyerManagerAssign(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Requisition Assign Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Assign Test Function: " + exception.getMessage());
        }
    }
}