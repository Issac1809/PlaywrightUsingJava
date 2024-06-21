package com.procurement.invoice.woinvoice.approve;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceApprovalTest extends BaseTest {

    @Test
    public void WOInvoiceApprovalTestMethod(){
        try {
        woInvoiceApprovalInterface.WOInvoiceApprovalMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}