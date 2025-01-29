package com.classes.requisition.approve;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApproveTest extends BaseTest {

    @Test
    public void approve(){
        try {
            int status = iPrApprove.approve();

            Assert.assertEquals(200, status, "API call was not successful");
        } catch (Exception error) {
            System.out.println("Error in Requisition Approve Test Function: " + error.getMessage());
        }
    }
}