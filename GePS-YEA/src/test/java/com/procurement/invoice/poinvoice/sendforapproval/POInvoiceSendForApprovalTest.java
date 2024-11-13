package com.procurement.invoice.poinvoice.sendforapproval;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class POInvoiceSendForApprovalTest extends BaseTest {

    @Test
    public void POInvoiceSendForApprovalTestMethod(){
        try {
        poSendForApprovalInterface.SendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}