package com.source.classes.invoices.poinvoice.verify;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvVerifyTest extends BaseTest {

    @Test
    public void verify(){
        try {
            iInvVerify.verify();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Verify Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Verify Test Function: " + exception.getMessage());

        }
    }
}