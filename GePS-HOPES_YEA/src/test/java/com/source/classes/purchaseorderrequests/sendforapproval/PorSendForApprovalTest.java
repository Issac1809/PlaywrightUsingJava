package com.source.classes.purchaseorderrequests.sendforapproval;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorSendForApprovalTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void sendForApproval(String type, String purchaseType) {
        try {
            iPorSendForApproval.sendForApproval(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Send For Approval Test function: {}", exception.getMessage());
        }
    }
}