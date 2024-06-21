package com.procurement.invoice.poinvoice.checklist;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class ChecklistAcceptTest extends BaseTest {

    @Test
    public void ChecklistAcceptTestMethod(){
        try {
            poInvAccept.ChecklistAcceptMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}