package com.poc.classes.invoice.poinvoice.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iInvCreate.create();
            double finalGSTPercentage = iInvCreate.gst();
            iInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}