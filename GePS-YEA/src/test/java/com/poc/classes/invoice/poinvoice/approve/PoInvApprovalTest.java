package com.poc.classes.invoice.poinvoice.approve;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvApprovalTest extends BaseTest {

    @Test
    public void approval(){
        try {
            iInvApproval.approval();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}