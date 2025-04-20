package com.source.classes.requisitions.edit;//package com.classes.requisition.edit;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EditTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void edit(String type, String purchaseType){
        try {
            int status = iPrEdit.edit(type, purchaseType);
            Assert.assertEquals(200, status, "Requisition Edit was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Requisition Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Requisition Edit Test Function: " + exception.getMessage());
        }
    }
}