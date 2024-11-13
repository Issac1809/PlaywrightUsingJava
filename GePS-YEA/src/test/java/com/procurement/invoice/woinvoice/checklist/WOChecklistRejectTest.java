package com.procurement.invoice.woinvoice.checklist;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOChecklistRejectTest extends BaseTest {

    @Test
    public void ChecklistRejectTestMethod(){
        try {
            woInvChecklistReject.ChecklistRejectMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}