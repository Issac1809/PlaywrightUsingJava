package com.procurement.requisition.reject;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class NonCatalogPrRejectTest extends BaseTest {

    @Test (groups = "requisition")
    public void NonCatalogPrRejectTestMethod() throws InterruptedException {
        try {
            prReject.Reject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}