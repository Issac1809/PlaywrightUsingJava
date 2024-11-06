package com.procurement.poc.requisition.sendforapproval;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class SendForApprovalTest extends BaseTest {

    @Test
    public void assign() {
        try {
            iPrSendForApproval.sendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}