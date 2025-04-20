package com.source.classes.purchaseorderrequests.sendforapproval;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

public class PorSendForApprovalTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void sendForApproval(String type, String purchaseType) {
        try {
            List<String> getApprovers = iPorSendForApproval.getApprovers(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Send For Approval Test Function: {}", exception.getMessage());
        }
    }
}