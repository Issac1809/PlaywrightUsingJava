package com.poc.classes.invoice.woinvoice.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iWoInvCreate.create();
            double finalGSTPercentage = iWoInvCreate.gst();
            iWoInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}