package com.invoice;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceCreateTest extends BaseTest {

    @Test
    public void InvoiceCreateTestMethod(){
        poInvoiceCreateInterface.VendorCreatePOInvoice();
        double finalGSTPercentage = poInvoiceCreateInterface.VendorGST();
        double finalTotalGSTPercentage = poInvoiceCreateInterface.VendorTotalGST();
        poInvoiceCreateInterface.SGDEquivalentEnable(finalTotalGSTPercentage, finalGSTPercentage);
    }
}