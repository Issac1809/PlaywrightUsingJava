package com.procurement.poc.purchaseorderrequest.suspend;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorSuspendPorEditTest extends BaseTest {

    @Test
    public void PorSuspendPorEditTestMethod() throws InterruptedException{
        try {
        iPorSuspend.suspendPorEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}