package com.procurement.poc.requestforquotation.suspend;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RfqSuspendPrEditTest extends BaseTest {

    @Test
    public void RfqSuspendPrEdit() {
        try {
            iRfqSuspend.suspendPREdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}