package com.procurement.invoice.woinvoice.reject;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceRejectTest extends BaseTest {

    @Test
    public void InvoiceRejectTestMethod() {
        try {
            woInvReject.WOInvoiceRejectMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}