package com.requisition.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class BuyerNonCatalogPrSuspendTest extends BaseTest {

    @Test (priority = 8, groups = "requisition")
    public void BuyerNonCatalogPrSuspendTestMethod() throws InterruptedException {
        try {
            prSuspendBuyer.Suspend();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}