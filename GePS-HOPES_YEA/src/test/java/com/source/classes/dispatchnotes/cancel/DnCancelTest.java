package com.source.classes.dispatchnotes.cancel;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DnCancelTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void cancel(String type, String purchaseType) {
        try {
            iDnCancel.cancel(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Cancel Test function: {}", exception.getMessage());
            Assert.fail("Exception in DN Cancel Test Function: " + exception.getMessage());
        }
    }
}