package com.poc.classes.invoice.poinvoice.checklist;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvChecklistAcceptTest extends BaseTest {

    @Test
    public void accept(){
        try {
            iInvChecklistAccept.accept();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}