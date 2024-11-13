package com.poc.classes.purchaseorderrequest.sendforapproval;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;
import java.util.List;

public class PorSendForApprovalTest extends BaseTest {

    @Test
    public void sendForApproval() {
        try {
            List<String> getApprovers = iPorSendForApproval.getApprovers();
        } catch (Exception exception) {
            System.out.println("What is the Error: " + exception.getMessage());
        }
    }
}