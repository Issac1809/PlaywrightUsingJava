package com.source.classes.purchaseorderrequests.approve;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorApproveTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void approvalAndApprove(String type, String purchaseType){
        try {
            iPorApprove.approve(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Approve Test Function: {}", exception.getMessage());
            Assert.fail("Exception in POR Approve Test Function: " + exception.getMessage());
        }
    }
}