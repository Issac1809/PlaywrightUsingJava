package com.classes.requisition.suspend;
import com.base.BaseTest;
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
