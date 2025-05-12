package com.source.classes.invoices.poinvoice.edit;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvEditTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Edit")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Vendor Can Able To Edit The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Edit Test")
    public void edit(){
        try {
            iInvEdit.edit();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Edit Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Edit Test Function: " + exception.getMessage());

        }
    }
}