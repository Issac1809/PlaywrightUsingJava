package com.procurement.purchaseorder;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class POSendForVendorTest extends BaseTest {

    @Test
    public void POSendForVendorTestMethod(){
        try {
        purchaseOrderInterface.SendForVendor();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}