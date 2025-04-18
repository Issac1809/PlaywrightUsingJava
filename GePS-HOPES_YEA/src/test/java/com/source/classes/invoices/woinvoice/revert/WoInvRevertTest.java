package com.source.classes.invoices.woinvoice.revert;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvRevertTest extends BaseTest {

    @Test
    public void revert() {
        try {
            iWoInvRevert.revert();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Revert Test function: {}", exception.getMessage());
        }
    }
}