package com.source.classes.invoices.poinvoice.checklist;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvChecklistRejectTest extends BaseTest {

    @Test
    public void reject(){
        try {
            iInvReject.reject();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice CheckList Reject Test function: {}", exception.getMessage());
        }
    }
}