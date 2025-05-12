package com.source.classes.invoices.poinvoice.create;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvCreateTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Create")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Vendor Can Able To Create The Purchase Order Invoice")
    @Test(description = "Purchase Order Invoice Create Test")
    public void create(){
        try {
            iInvCreate.create();
            double finalGSTPercentage = iInvCreate.gst();
            iInvCreate.ifSgdEnable(finalGSTPercentage);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Create Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Create Test Function: " + exception.getMessage());

        }
    }
}