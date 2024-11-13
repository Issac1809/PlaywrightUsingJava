package com.poc.classes.requisition.suspend;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class BuyerSuspendTest extends BaseTest {

    @Test
    public void suspend() {
        try {
            iPrBuyerSuspend.suspend();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}