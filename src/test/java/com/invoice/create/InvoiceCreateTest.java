package com.invoice.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceCreateTest extends BaseTest {

    @Test
    public void InvoiceCreateTestMethod(){
        poInvoiceCreateInterface.VendorCreatePOInvoice();
        double finalGSTPercentage = poInvoiceCreateInterface.VendorGST();
        poInvoiceCreateInterface.SGDEquivalentEnable(finalGSTPercentage);
    }
}