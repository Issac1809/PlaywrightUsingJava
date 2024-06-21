package com.procurement.purchaseorderrequest.approvalandapprove;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PorApprovalAndApproveTest extends BaseTest {

    @Test
    public void PorApprovalAndApproveTestMethod(){
        try {
        approvalAndApprove.PorApprovalAndApproveMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}