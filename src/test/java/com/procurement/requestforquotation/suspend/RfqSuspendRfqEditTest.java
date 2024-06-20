package com.procurement.requestforquotation.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RfqSuspendRfqEditTest extends BaseTest {

    @Test
    public void RfqSuspendRfqEditTestMethod() throws InterruptedException {
        try {
        rfqSuspend.SuspendRfqEdit();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}