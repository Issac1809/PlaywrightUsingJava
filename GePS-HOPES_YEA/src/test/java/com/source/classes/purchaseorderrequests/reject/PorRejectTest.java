package com.source.classes.purchaseorderrequests.reject;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorRejectTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void reject() {
        try {
            String type = "";
            String purchaseType = "";

            iPorReject.porReject(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Reject Test Function: {}", exception.getMessage());
        }
    }
}