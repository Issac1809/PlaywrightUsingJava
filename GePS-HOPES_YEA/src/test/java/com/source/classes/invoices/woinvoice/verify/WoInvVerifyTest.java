package com.source.classes.invoices.woinvoice.verify;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvVerifyTest extends BaseTest {

    @Epic("Work Order Verify")
    @Feature("Work Order Invoice Verify")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Verify The Work Order Invoice")
    @Test(description = "Work Order Invoice Verify Test")
    public void verify(){
        try {
            iWoInvVerify.verify();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Verify Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Verify Test Function: " + exception.getMessage());

        }
    }
}