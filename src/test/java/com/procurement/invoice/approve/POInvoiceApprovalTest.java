package com.procurement.invoice.approve;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class POInvoiceApprovalTest extends BaseTest {

    @Test
    public void POInvoiceApprovalTestMethod(){
        try {
        poInvoiceApprovalInterface.POInvoiceApprovalMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}