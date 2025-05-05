package com.source.classes.invoices.woinvoice.checklist;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoInvChecklistAcceptTest extends BaseTest {

    @Test
    public void accept(){
        try {
            iWoInvChecklistAccept.accept();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice CheckList Accept Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Invoice Checklist Accept Test Function: " + exception.getMessage());

        }
    }
}