package com.procurement.invoice.woinvoice.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOInvoiceCreateTest extends BaseTest {

    @Test
    public void InvoiceCreateTestMethod(){
        try {
        woInvoiceCreateInterface.VendorCreateWOInvoice();
        double finalGSTPercentage = woInvoiceCreateInterface.VendorGST();
        woInvoiceCreateInterface.SGDEquivalentEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}