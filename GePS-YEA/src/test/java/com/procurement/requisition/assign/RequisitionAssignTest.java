package com.procurement.requisition.assign;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RequisitionAssignTest extends BaseTest {

    @Test (groups = "requisition")
    public void RequisitionAssignTestMethod() {
        try {
            prAssign.BuyerManagerLogin();
            prAssign.BuyerManagerAssign();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}