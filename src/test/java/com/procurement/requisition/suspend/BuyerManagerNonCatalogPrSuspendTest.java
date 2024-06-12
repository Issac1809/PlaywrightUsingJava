package com.procurement.requisition.suspend;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class BuyerManagerNonCatalogPrSuspendTest extends BaseTest {

    @Test (groups = "requisition")
    public void BuyerManagerNonCatalogPrSuspendTestMethod() throws InterruptedException {
        try {
            prSuspendBuyerManager.Suspend();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}