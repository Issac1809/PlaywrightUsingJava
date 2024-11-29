package com.poc.classes.invoice.poinvoice.verify;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvVerifyTest extends BaseTest {

    @Test
    public void verify(){
        try {
            iInvVerify.verify();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}