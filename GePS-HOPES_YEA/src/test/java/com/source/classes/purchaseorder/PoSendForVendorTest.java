package com.source.classes.purchaseorder;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PoSendForVendorTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void sendForVendor(){
        try {
            String type = "";
            String purchaseType = "";
            iPoSendForVendor.sendPoForVendor(type, purchaseType);
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}