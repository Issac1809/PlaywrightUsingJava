package com.procurement.invoice.poinvoice.invreturn;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceReturnTest extends BaseTest {

    @Test
    public void InvoiceReturnTestMethod(){
        try {
            poInvReturn.POInvoiceReturnMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}