package com.poc.classes.workorders.trackerstatus;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoTrackerStatusTest extends BaseTest {

    @Test
    public void trackerStatus(){
        try {
            iWoTrackerStatus.trackerStatus();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}