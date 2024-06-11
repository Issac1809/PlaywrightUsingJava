package com.requisition.assign;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RequisitionAssignTest extends BaseTest {

    @Test (priority = 7, groups = "requisition")
    public void RequisitionAssignTestMethod() {
        try {
            prAssign.BuyerManagerLogin();
            prAssign.BuyerManagerAssign();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}