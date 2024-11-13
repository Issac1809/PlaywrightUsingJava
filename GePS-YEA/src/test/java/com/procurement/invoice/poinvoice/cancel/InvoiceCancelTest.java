package com.procurement.invoice.poinvoice.cancel;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceCancelTest extends BaseTest {

    @Test
    public void InvoiceCancelTestMethod(){
        try {
            poInvCancel.PoInvoiceCancelMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}