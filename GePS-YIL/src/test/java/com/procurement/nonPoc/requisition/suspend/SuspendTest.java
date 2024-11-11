package com.procurement.nonPoc.requisition.suspend;
import com.procurement.nonPoc.base.BaseTest;
import org.testng.annotations.Test;

public class SuspendTest extends BaseTest {

    @Test
    public void suspend() {
        try {
            iPrSuspend.suspend();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}