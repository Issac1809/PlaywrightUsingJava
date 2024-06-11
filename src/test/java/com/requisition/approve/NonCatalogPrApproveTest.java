package com.requisition.approve;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class NonCatalogPrApproveTest extends BaseTest {

    @Test (priority = 5, groups = "requisition")
    public void NonCatalogPrApproveTestMethod(){
        try {
            prApprove.Approve();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}