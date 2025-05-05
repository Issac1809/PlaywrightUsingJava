package com.source.classes.invoices.woinvoice.hold;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvHoldTest extends BaseTest {

    @Test
    public void hold(){
        try {
            iWoInvHold.hold();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Hold Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Hold Test Function: " + exception.getMessage());

        }
    }
}