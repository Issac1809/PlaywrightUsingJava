package com.source.classes.invoices.poinvoice.cancel;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PoInvCancelTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Cancel")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Cancel The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Cancel Test")
    @Parameters("type")
    public void cancel(String type){
        try {
            iInvCancel.cancel(type);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Cancel Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Cancel Test Function: " + exception.getMessage());
        }
    }
}