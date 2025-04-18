package com.source.classes.invoices.poinvoice.hold;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvHoldTest extends BaseTest {

    @Test
    public void hold(){
        try {
            iInvHold.hold();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Hold Test function: {}", exception.getMessage());
        }
    }
}