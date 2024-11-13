package com.procurement.invoice.poinvoice.revert;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceRevertTest extends BaseTest {

    @Test
    public void InvoiceRevertTestMethod() {
        try {
            poInvRevert.POInvoiceRevertMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}