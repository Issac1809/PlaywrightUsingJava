package com.poc.classes.invoice.woinvoice.hold;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvHoldTest extends BaseTest {

    @Test
    public void hold(){
        try {
            iWoInvHold.hold();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}