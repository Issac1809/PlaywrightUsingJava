package com.procurement.invoice.poinvoice.approve;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class POInvoiceApprovalTest extends BaseTest {

    @Test
    public void POInvoiceApprovalTestMethod(){
        try {
        poInvoiceApprovalInterface.POInvoiceApprovalMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}