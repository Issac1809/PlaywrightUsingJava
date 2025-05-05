package com.source.classes.orderschedules.reject;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OsRejectTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void reject(String type, String purchaseType) {
        try {
            iOsReject.reject(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in OS Reject Test Function: {}", exception.getMessage());
            Assert.fail("Exception in OS Reject Test Function: " + exception.getMessage());
        }
    }
}