package com.procurement.purchaseorderrequest.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PorSuspendRfqEditTest extends BaseTest {

    @Test
    public void PorSuspendRfqEditTestMethod(){
        try {
        porSuspend.SuspendRfqEdit();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}