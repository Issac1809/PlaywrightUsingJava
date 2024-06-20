package com.procurement.requestforquotation.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RfqSuspendPrEditTest extends BaseTest {

    @Test
    public void RfqSuspendPrEditTestMethod() throws InterruptedException {
        try {
        rfqSuspend.SuspendPREdit();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
