package com.poc.classes.invoice.woinvoice.cancel;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvCancelTest extends BaseTest {

    @Test
    public void cancel(){
        try {
            iWoInvCancel.cancel();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}