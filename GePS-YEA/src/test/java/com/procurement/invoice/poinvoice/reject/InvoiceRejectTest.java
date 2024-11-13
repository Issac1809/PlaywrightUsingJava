package com.procurement.invoice.poinvoice.reject;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceRejectTest extends BaseTest {

    @Test
    public void InvoiceRejectTestMethod() {
        try {
            poInvReject.POInvoiceRejectMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}