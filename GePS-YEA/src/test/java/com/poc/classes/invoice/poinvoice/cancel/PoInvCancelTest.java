package com.poc.classes.invoice.poinvoice.cancel;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvCancelTest extends BaseTest {

    @Test
    public void cancel(){
        try {
            iInvCancel.cancel();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}