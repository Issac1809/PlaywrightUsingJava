package com.poc.classes.invoice.woinvoice.checklist;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvChecklistAcceptTest extends BaseTest {

    @Test
    public void accept(){
        try {
            iWoInvChecklistAccept.accept();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}