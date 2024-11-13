package com.procurement.invoice.woinvoice.cancel;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceCancelTest extends BaseTest {

    @Test
    public void InvoiceCancelTestMethod(){
        try {
            woInvCancel.WoInvoiceCancelMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}