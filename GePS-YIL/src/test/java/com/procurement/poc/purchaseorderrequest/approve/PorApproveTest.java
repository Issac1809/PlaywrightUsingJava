package com.procurement.poc.purchaseorderrequest.approve;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorApproveTest extends BaseTest {

    @Test
    public void approve(){
        try {
//            iPorApprove.approverLogin();
        iPorApprove.approve();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
