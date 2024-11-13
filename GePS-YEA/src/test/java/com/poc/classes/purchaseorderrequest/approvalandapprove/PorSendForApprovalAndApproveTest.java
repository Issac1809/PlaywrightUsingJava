package com.poc.classes.purchaseorderrequest.approvalandapprove;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorSendForApprovalAndApproveTest extends BaseTest {

    @Test
    public void approvalAndApprove(){
        try {
            iPorSendForApprovalAndApprove.approvalAndApprove();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}