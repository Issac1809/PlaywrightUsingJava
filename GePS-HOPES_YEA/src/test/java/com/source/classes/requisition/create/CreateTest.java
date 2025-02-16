package com.source.classes.requisition.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void create(){
        try{
            String purchaseType = "";
            String type = "";
            int status = iPrType.processRequisitionType(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Create was not Successful");
        } catch (Exception exception) {
            logger.error("Error in Requisition Create Test Function: {}", exception.getMessage());
            Assert.fail("Error in Requisition Create Test Function: " + exception.getMessage());
        }
    }
}