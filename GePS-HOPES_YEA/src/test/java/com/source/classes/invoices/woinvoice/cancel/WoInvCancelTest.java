package com.source.classes.invoices.woinvoice.cancel;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvCancelTest extends BaseTest {

    @Test
    public void cancel(){
        try {
            iWoInvCancel.cancel();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Cancel Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Cancel Test Function: " + exception.getMessage());

        }
    }
}