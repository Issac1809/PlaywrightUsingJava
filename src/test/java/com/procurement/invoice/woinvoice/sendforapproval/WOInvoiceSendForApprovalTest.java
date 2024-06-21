package com.procurement.invoice.woinvoice.sendforapproval;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceSendForApprovalTest extends BaseTest {

    @Test
    public void WOInvoiceSendForApprovalTestMethod(){
        try {
        woSendForApprovalInterface.SendForApproval();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
