package com.procurement.invoice.woinvoice.approve;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceApprovalTest extends BaseTest {

    @Test
    public void WOInvoiceApprovalTestMethod(){
        try {
        woInvoiceApprovalInterface.WOInvoiceApprovalMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}