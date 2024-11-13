package com.procurement.invoice.woinvoice.invreturn;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceReturnTest extends BaseTest {

    @Test
    public void InvoiceReturnTestMethod(){
        try {
            woInvReturn.WOInvoiceReturnMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}