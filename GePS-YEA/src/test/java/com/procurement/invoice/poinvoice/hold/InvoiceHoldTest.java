package com.procurement.invoice.poinvoice.hold;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceHoldTest extends BaseTest {

    @Test
    public void InvoiceHoldTestMethod(){
        try {
            poInvHold.POInvoiceHoldMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}