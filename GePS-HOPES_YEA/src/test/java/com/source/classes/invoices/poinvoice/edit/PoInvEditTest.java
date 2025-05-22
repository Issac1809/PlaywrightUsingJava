package com.source.classes.invoices.poinvoice.edit;
import com.base.BaseTest;
import com.util.PoInvTrnUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvEditTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice Edit")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Vendor Can Able To Edit The Purchase Order Invoice")
    @Test(dataProvider = "invoiceData", dataProviderClass = PoInvTrnUtil.class, description = "Purchase Order Invoice Edit Test")
    public void edit(String referenceId, String transactionId, String uid){
        try {
            iInvEdit.edit(referenceId, transactionId, uid);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Edit Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Edit Test Function: " + exception.getMessage());
        }
    }
}