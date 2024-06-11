package com.requisition.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class BuyerManagerNonCatalogPrSuspendTest extends BaseTest {

    @Test (priority = 6, groups = "requisition")
    public void BuyerManagerNonCatalogPrSuspendTestMethod() throws InterruptedException {
        try {
            prSuspendBuyerManager.Suspend();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}