package com.procurement.invoice.sendforapproval;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class POInvoiceSendForApprovalTest extends BaseTest {

    @Test
    public void POInvoiceSendForApprovalTestMethod(){
        try {
        poSendForApprovalInterface.SendForApproval();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
