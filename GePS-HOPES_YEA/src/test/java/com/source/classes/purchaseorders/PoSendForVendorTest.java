package com.source.classes.purchaseorders;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PoSendForVendorTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void sendForVendor(String type, String purchaseType) {
        try {
            iPoSendForVendor.sendPoForVendor(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Send For Vendor Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Send For Vendor Test Function: " + exception.getMessage());
        }
    }
}