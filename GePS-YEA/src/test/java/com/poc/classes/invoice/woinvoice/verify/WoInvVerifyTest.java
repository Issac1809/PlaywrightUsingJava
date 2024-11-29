package com.poc.classes.invoice.woinvoice.verify;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvVerifyTest extends BaseTest {

    @Test
    public void verify(){
        try {
            iWoInvVerify.verify();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}