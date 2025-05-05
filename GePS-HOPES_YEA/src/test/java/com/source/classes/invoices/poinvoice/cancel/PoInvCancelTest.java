package com.source.classes.invoices.poinvoice.cancel;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvCancelTest extends BaseTest {

    @Test
    public void cancel(){
        try {
            iInvCancel.cancel();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Cancel Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Cancel Test Function: " + exception.getMessage());

        }
    }
}