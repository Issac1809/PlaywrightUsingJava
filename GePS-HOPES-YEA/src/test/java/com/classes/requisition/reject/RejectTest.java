package com.classes.requisition.reject;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RejectTest extends BaseTest {

    @Test
    public void reject() {
        try {
            int status = iPrReject.reject();

            Assert.assertEquals(200, status, "API call was not successful");
        } catch (Exception error) {
            System.out.println("Error in Requisition Reject Test Function: " + error.getMessage());
        }
    }
}