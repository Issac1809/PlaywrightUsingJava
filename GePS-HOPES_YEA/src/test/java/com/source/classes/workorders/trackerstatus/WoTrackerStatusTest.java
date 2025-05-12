package com.source.classes.workorders.trackerstatus;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoTrackerStatusTest extends BaseTest {

    @Epic("Work Orders")
    @Feature("Work Orders Tracker Status")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Vendor Can Able To Update The Work Order Tracker Status")
    @Test(description = "Work Order Tracker Status Test")
    public void trackerStatus(){
        try {
            iWoTrackerStatus.trackerStatus();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Tracker Status Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Tracker Status Test Function: " + exception.getMessage());
        }
    }
}