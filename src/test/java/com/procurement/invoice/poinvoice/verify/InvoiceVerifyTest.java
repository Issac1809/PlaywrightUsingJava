package com.procurement.invoice.poinvoice.verify;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceVerifyTest extends BaseTest {

    @Test
    public void InvoiceVerifyTestMethod(){
        try {
            poInvVerify.POInvoiceVerifyMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}