package com.source.classes.invoices.poinvoice.revert;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvRevertTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Revert")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Revert The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Revert Test")
    public void revert() {
        try {
            iInvRevert.revert();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Revert Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Revert Test Function: " + exception.getMessage());

        }
    }
}