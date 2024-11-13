package com.procurement.invoice.woinvoice.revert;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceRevertTest extends BaseTest {

    @Test
    public void InvoiceRevertTestMethod() {
        try {
            woInvRevert.WOInvoiceRevertMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}