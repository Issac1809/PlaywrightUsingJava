package com.source.classes.invoices.woinvoice.checklist;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvChecklistRejectTest extends BaseTest {

    @Test
    public void reject(){
        try {
            iWoInvReject.reject();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice CheckList Reject Test function: {}", exception.getMessage());
        }
    }
}