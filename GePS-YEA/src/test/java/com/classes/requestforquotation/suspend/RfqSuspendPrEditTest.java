package com.classes.requestforquotation.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RfqSuspendPrEditTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void RfqSuspendPrEditTestMethod() throws InterruptedException {
        try {
        rfqSuspend.SuspendPREdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}