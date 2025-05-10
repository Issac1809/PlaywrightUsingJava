package com.source.classes.purchaseorderrequests.revision;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorRevisionTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void porRevision(String type, String purchaseType) {
        try {
            iPorRevision.porRevision(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Send For Vendor Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Send For Vendor Test Function: " + exception.getMessage());
        }
    }
}
