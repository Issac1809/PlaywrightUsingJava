package com.procurement.poc.requisition.reject;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RejectTest extends BaseTest {

    @Test
    public void reject(String approver) {
        try {
            iPrReject.reject(approver);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}