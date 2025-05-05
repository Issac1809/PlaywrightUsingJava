package com.source.classes.invoices.poinvoice.revert;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoInvRevertTest extends BaseTest {

    @Test
    public void revert() {
        try {
            iInvRevert.revert();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Revert Test function: {}", exception.getMessage());
            Assert.fail("Exception in PO Invoice Revert Test Function: " + exception.getMessage());

        }
    }
}