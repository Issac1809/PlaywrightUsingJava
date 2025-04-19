package com.source.classes.invoices.poinvoice.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iInvCreate.create();
            double finalGSTPercentage = iInvCreate.gst();
            iInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Create Test function: {}", exception.getMessage());
        }
    }
}