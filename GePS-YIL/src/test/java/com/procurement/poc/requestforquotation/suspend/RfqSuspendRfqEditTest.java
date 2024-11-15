package com.procurement.poc.requestforquotation.suspend;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RfqSuspendRfqEditTest extends BaseTest {

    @Test
    public void RfqSuspendRfqEditTestMethod() {
        try {
            iRfqSuspend.suspendRfqEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}