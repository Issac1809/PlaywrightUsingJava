package com.source.classes.purchaseorderrequests.reject;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorRejectTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void reject(String type, String purchaseType) {
        try {
            iPorReject.porReject(type, purchaseType);
            iPorEdit.porEdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Reject Test Function: {}", exception.getMessage());
            Assert.fail("Exception in POR Reject Test Function: " + exception.getMessage());
        }
    }
}