package com.procurement.invoice.woinvoice.sendforapproval;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceSendForApprovalTest extends BaseTest {

    @Test
    public void WOInvoiceSendForApprovalTestMethod(){
        try {
        woSendForApprovalInterface.SendForApproval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}