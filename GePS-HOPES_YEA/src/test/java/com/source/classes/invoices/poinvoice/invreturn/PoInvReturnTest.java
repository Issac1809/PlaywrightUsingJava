package com.source.classes.invoices.poinvoice.invreturn;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvReturnTest extends BaseTest {

    @Test
    public void returnMethod(){
        try {
            iInvReturn.invReturn();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Return Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Return Test Function: " + exception.getMessage());

        }
    }
}