package com.source.classes.requisition.reject;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RejectTest extends BaseTest {

    @Test@Parameters({"purchaseType"})
    public void reject() {
        try {
            String purchaseType = "";
            int status = iPrReject.reject(purchaseType);

            Assert.assertEquals(200, status, "API call was not successful");
        } catch (Exception error) {
            logger.error("Error in Requisition Reject Test Function: " + error.getMessage());
        }
    }
}