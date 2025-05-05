package com.source.classes.invoices.woinvoice.verify;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvVerifyTest extends BaseTest {

    @Test
    public void verify(){
        try {
            iWoInvVerify.verify();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Verify Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Verify Test Function: " + exception.getMessage());

        }
    }
}