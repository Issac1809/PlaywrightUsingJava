package com.source.classes.invoices.woinvoice.invreturn;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvReturnTest extends BaseTest {

    @Test
    public void returnMethod(){
        try {
            iWoInvReturn.returnMethod();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Return Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Return Test Function: " + exception.getMessage());

        }
    }
}