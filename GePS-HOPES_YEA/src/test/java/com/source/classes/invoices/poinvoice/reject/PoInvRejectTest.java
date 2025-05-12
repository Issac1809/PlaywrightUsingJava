package com.source.classes.invoices.poinvoice.reject;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvRejectTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Reject")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Approvers Can Able To Reject The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Reject Test")
    public void reject() {
        try {
            iInvReject.reject();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Reject Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Reject Test Function: " + exception.getMessage());

        }
    }
}