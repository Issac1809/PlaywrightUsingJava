package com.source.classes.invoices.woinvoice.reject;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvRejectTest extends BaseTest {

    @Test
    public void reject() {
        try {
            iWoInvReject.reject();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Reject Test function: {}", exception.getMessage());
        }
    }
}