package com.procurement.invoice.woinvoice.checklist;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOChecklistAcceptTest extends BaseTest {

    @Test
    public void ChecklistAcceptTestMethod(){
        try {
            woInvAccept.ChecklistAcceptMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}