package com.source.classes.invoices.poinvoice.checklist;
import com.base.BaseTest;
import com.util.PoInvTrnUtil;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvChecklistRejectTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice CheckList Reject")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Reject The Purchase Order Invoice Checklist")
    @Test(dataProvider = "invoiceData", dataProviderClass = PoInvTrnUtil.class, description = "Purchase Order Invoice CheckList Reject Test")
    public void reject(String referenceId, String transactionId, String uid){
        try {
            iInvReject.reject(referenceId, transactionId, uid);
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice CheckList Reject Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Checklist Reject Test Function: " + exception.getMessage());
        }
    }
}