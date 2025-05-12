package com.source.classes.invoices.poinvoice.hold;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvHoldTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Hold")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Hold The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Hold Test")
    public void hold(){
        try {
            iInvHold.hold();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Hold Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Hold Test Function: " + exception.getMessage());

        }
    }
}