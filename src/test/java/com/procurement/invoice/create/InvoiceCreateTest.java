package com.procurement.invoice.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceCreateTest extends BaseTest {

    @Test
    public void InvoiceCreateTestMethod(){
        try {
        poInvoiceCreateInterface.VendorCreatePOInvoice();
        double finalGSTPercentage = poInvoiceCreateInterface.VendorGST();
        poInvoiceCreateInterface.SGDEquivalentEnable(finalGSTPercentage);
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}