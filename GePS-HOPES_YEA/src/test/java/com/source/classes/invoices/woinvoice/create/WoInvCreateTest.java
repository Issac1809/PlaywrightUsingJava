package com.source.classes.invoices.woinvoice.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iWoInvCreate.create();
            double finalGSTPercentage = iWoInvCreate.gst();
            iWoInvCreate.ifSgdEnable(finalGSTPercentage);
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Create Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Create Test Function: " + exception.getMessage());

        }
    }
}