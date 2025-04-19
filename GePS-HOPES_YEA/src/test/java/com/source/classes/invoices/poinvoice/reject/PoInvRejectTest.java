package com.source.classes.invoices.poinvoice.reject;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvRejectTest extends BaseTest {

    @Test
    public void reject() {
        try {
            iInvReject.reject();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice Reject Test function: {}", exception.getMessage());
        }
    }
}