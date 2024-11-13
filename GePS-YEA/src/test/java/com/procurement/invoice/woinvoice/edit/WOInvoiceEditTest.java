package com.procurement.invoice.woinvoice.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceEditTest extends BaseTest {

    @Test
    public void InvoiceEditTestMethod(){
        try {
            woInvEdit.WOInvoiceEditMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}