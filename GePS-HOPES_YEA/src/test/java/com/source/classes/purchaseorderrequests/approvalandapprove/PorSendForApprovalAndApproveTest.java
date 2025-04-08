package com.source.classes.purchaseorderrequests.approvalandapprove;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorSendForApprovalAndApproveTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void approvalAndApprove(){
        try {
            String type = "";
            String purchaseType = "";

            iPorSendForApprovalAndApprove.approvalAndApprove(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Send For Approval and Approve Test Function: {}", exception.getMessage());
        }
    }
}