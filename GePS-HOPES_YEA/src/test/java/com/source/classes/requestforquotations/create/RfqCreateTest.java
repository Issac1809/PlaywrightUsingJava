package com.source.classes.requestforquotation.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqCreateTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void create() {
        try {
            String type = "";
            iRfqCreate.buyerRfqCreate(type);
        } catch (Exception exception) {
            logger.error("Exception in RFQ Create Test Function: {}", exception.getMessage());
            Assert.fail("Exception in RFQ Create Test Function: " + exception.getMessage());
        }
    }
}