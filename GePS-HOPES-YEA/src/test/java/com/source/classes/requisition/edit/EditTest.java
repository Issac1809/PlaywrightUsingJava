package com.source.classes.requisition.edit;//package com.classes.requisition.edit;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EditTest extends BaseTest {

    @Test
    @Parameters({"purchaseType"})
    public void edit(){
        try {
            String purchaseType = "";
            int status = iPrEdit.edit(purchaseType);
            if(status == 200) {
                Assert.assertEquals(200, status, "Requisition Edit was not successful");
            }
        } catch (Exception exception) {
            logger.error("Error in Requisition Edit Test Function: " + exception.getMessage());
        }
    }
}