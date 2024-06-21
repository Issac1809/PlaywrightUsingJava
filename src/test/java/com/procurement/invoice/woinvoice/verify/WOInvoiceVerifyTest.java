package com.procurement.invoice.woinvoice.verify;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceVerifyTest extends BaseTest {

    @Test
    public void InvoiceVerifyTestMethod(){
        try {
            woInvVerify.WOInvoiceVerifyMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}