package com.poc.classes.purchaseorderrequest.suspend;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorSuspendPorEditTest extends BaseTest {

    @Test
    public void suspend() throws InterruptedException{
        try {
            iPorSuspend.suspendPorEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}