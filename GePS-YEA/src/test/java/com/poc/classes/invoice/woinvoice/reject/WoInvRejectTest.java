package com.poc.classes.invoice.woinvoice.reject;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvRejectTest extends BaseTest {

    @Test
    public void reject() {
        try {
            iWoInvReject.reject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}