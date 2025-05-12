package com.source.classes.invoices.poinvoice.checklist;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvChecklistRejectTest extends BaseTest {

    @Epic("Purchase Order Invoice")
    @Feature("Purchase Order Invoice CheckList Reject")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Reject The Purchase Order Invoice Checklist")
    @Test(description = "Purchase Order Invoice CheckList Reject Test")
    public void reject(){
        try {
            iInvReject.reject();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice CheckList Reject Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Checklist Reject Test Function: " + exception.getMessage());

        }
    }
}