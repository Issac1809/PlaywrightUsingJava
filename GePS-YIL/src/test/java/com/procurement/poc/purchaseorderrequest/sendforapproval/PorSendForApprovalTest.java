package com.procurement.poc.purchaseorderrequest.sendforapproval;

import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class PorSendForApprovalTest extends BaseTest {

    @Test
    public void sendForApproval() {
        try {
//            List<String> getApprovers = iPorSendForApproval.getApprovers();
            iPorSendForApproval.sendForApproval();
        } catch (Exception exception) {
            System.out.println("What is the Error: " + exception.getMessage());
        }
    }
}