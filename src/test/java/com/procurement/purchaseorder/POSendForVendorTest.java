package com.procurement.purchaseorder;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class POSendForVendorTest extends BaseTest {

    @Test
    public void POSendForVendorTestMethod(){
        purchaseOrderInterface.SendForVendor();
    }
}