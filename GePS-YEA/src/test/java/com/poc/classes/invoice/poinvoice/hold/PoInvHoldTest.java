package com.poc.classes.invoice.poinvoice.hold;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvHoldTest extends BaseTest {

    @Test
    public void hold(){
        try {
            iInvHold.hold();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}