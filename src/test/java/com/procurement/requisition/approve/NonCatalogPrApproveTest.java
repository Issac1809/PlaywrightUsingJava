package com.procurement.requisition.approve;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class NonCatalogPrApproveTest extends BaseTest {

    @Test (groups = "requisition")
    public void NonCatalogPrApproveTestMethod(){
        try {
            prApprove.Approve();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}