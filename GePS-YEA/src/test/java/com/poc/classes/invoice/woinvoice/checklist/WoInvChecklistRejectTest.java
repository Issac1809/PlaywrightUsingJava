package com.poc.classes.invoice.woinvoice.checklist;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvChecklistRejectTest extends BaseTest {

    @Test
    public void reject(){
        try {
            iWoInvReject.reject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}