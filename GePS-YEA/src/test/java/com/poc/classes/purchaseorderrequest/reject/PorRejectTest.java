package com.poc.classes.purchaseorderrequest.reject;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorRejectTest extends BaseTest {

    @Test
    public void reject() {
        try {
            iPorReject.porReject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}