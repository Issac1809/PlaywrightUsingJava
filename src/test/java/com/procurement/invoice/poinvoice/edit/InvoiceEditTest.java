package com.procurement.invoice.poinvoice.edit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InvoiceEditTest extends BaseTest {

    @Test
    public void InvoiceEditTestMethod(){
        try {
            poInvEdit.POInvoiceEditMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}