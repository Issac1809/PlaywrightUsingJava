package com.poc.classes.invoice.poinvoice.reject;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvRejectTest extends BaseTest {

    @Test
    public void reject() {
        try {
            iInvReject.reject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}