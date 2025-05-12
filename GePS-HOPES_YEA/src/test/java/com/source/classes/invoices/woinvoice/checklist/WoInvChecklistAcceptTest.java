package com.source.classes.invoices.woinvoice.checklist;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvChecklistAcceptTest extends BaseTest {

    @Epic("Work Order Checklist Accept")
    @Feature("Work Order Invoice Checklist Accept")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Verifier Can Able To Accept The Work Order Invoice Checklist")
    @Test(description = "Work Order Invoice Checklist Accept Test")
    public void accept(){
        try {
            iWoInvChecklistAccept.accept();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice CheckList Accept Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Checklist Accept Test Function: " + exception.getMessage());

        }
    }
}