package com.procurement.nonPoc.requisition.approve;
import com.procurement.nonPoc.base.BaseTest;
import org.testng.annotations.Test;

public class ApproveTest extends BaseTest {

    @Test
    public void approve(){
        try {
            iPrApprove.approve();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}