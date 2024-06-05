package com.requisition.assign;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RequisitionAssignTest extends BaseTest {

    @Test
    public void RequisitionAssignTestMethod() {
        try {
            prAssign.BuyerManagerLogin();
            prAssign.BuyerManagerAssign();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}