package com.source.classes.invoices.poinvoice.checklist;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvChecklistAcceptTest extends BaseTest {

    @Test
    public void accept(){
        try {
            iInvChecklistAccept.accept();
        } catch (Exception exception) {
            logger.error("Exception in PO Invoice CheckList Accept Test function: {}", exception.getMessage());
        }
    }
}