package com.source.classes.purchaseorderrequests.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorCreateTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void create(String type, String purchaseType){
        try {
            int status = iPorCreate.porCreate(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Create was not Successful");
        } catch (Exception exception) {
            logger.error("Exception in POR Create Test Function: {}", exception.getMessage());
            Assert.fail("Exception in POR Create Test Function: " + exception.getMessage());
        }
    }
}