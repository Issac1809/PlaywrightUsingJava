package com.source.classes.requestforquotations.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqCreateTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void create(String type) {
        try {
            int status = iRfqCreate.buyerRfqCreate(type);
            Assert.assertEquals(200, status, "Requisition Approve was not successful");
        } catch (Exception exception) {
            logger.error("Exception in RFQ Create Test Function: {}", exception.getMessage());
            Assert.fail("Exception in RFQ Create Test Function: " + exception.getMessage());
        }
    }
}