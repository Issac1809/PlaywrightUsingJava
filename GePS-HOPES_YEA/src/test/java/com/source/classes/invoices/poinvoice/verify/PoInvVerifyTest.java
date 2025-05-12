package com.source.classes.invoices.poinvoice.verify;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvVerifyTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Verify")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Verify The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Verify Test")
    public void verify(){
        try {
            iInvVerify.verify();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Verify Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Verify Test Function: " + exception.getMessage());

        }
    }
}