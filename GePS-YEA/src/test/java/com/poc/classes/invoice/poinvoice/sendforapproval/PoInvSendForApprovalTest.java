package com.poc.classes.invoice.poinvoice.sendforapproval;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvSendForApprovalTest extends BaseTest {

    @Test
    public void sendForApproval(){
        try {
            iInvSendForApproval.sendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}