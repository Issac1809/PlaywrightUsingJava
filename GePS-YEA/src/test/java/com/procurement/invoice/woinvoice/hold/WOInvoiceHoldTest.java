package com.procurement.invoice.woinvoice.hold;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceHoldTest extends BaseTest {

    @Test
    public void InvoiceHoldTestMethod(){
        try {
            woInvHold.WOInvoiceHoldMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}