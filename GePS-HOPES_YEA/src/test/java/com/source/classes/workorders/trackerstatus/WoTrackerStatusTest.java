package com.source.classes.workorders.trackerstatus;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WoTrackerStatusTest extends BaseTest {

    @Test
    public void trackerStatus(){
        try {
            iWoTrackerStatus.trackerStatus();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Tracker Status Test function: {}", exception.getMessage());
        }
    }
}