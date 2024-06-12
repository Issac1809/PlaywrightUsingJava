package com.procurement.invoice.sendforapproval;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class POInvoiceSendForApprovalTest extends BaseTest {

    @Test
    public void POInvoiceSendForApprovalTestMethod(){
        poSendForApprovalInterface.SendForApproval();
    }
}
