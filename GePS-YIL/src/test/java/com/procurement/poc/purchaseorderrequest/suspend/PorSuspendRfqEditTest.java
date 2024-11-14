package com.procurement.poc.purchaseorderrequest.suspend;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorSuspendRfqEditTest extends BaseTest {

    @Test
    public void PorSuspendRfqEditTestMethod(){
        try {
        iPorSuspend.suspendRfqEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}