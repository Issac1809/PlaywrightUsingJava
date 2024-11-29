package com.poc.classes.invoice.poinvoice.checklist;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvChecklistRejectTest extends BaseTest {

    @Test
    public void reject(){
        try {
            iInvReject.reject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}