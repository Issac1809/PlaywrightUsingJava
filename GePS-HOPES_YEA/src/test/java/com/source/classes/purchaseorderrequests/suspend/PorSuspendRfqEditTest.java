package com.poc.classes.purchaseorderrequest.suspend;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorSuspendRfqEditTest extends BaseTest {

    @Test
    public void suspend(){
        try {
            iPorSuspend.suspendRfqEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}