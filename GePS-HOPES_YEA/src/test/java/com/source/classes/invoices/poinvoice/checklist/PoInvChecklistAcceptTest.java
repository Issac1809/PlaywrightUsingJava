package com.source.classes.invoices.poinvoice.checklist;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvChecklistAcceptTest extends BaseTest {

    @Test
    public void accept(){
        try {
            iInvChecklistAccept.accept();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice CheckList Accept Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Checklist Accept Test Function: " + exception.getMessage());

        }
    }
}