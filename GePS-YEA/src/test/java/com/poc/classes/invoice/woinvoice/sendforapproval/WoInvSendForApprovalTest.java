package com.poc.classes.invoice.poinvoice.sendforapproval;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvSendForApprovalTest extends BaseTest {

    @Test
    public void sendForApproval(){
        try {
            iWoInvSendForApproval.sendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}