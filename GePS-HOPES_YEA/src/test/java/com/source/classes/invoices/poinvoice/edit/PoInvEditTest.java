package com.source.classes.invoices.poinvoice.edit;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvEditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iInvEdit.edit();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Edit Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Edit Test Function: " + exception.getMessage());

        }
    }
}