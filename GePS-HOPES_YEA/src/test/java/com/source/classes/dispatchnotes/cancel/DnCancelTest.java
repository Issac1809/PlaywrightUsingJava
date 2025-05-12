package com.source.classes.dispatchnotes.cancel;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DnCancelTest extends BaseTest {

    @Epic("Dispatch Notes")
    @Feature("Dispatch Notes Cancel")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Logistics Manager Can Cancel The Dispatch Notes")
    @Test(description = "Dispatch Notes Cancel Test")
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