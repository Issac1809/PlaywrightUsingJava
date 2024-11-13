package com.poc.classes.requisition.suspend;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class BuyerManagerSuspendTest extends BaseTest {

    @Test
    public void suspend() {
        try {
            iPrBuyerManagerSuspend.suspend();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
