package com.source.classes.invoices.poinvoice.invreturn;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvReturnTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Return")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Return The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Return Test")
    public void returnMethod(){
        try {
            iInvReturn.invReturn();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Return Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Return Test Function: " + exception.getMessage());

        }
    }
}