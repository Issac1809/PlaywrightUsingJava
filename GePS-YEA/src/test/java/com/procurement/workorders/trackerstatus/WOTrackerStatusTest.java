package com.procurement.workorders.trackerstatus;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WOTrackerStatusTest extends BaseTest {

    @Test
    public void WOTrackerStatusTestMethod(){
        try {
        woTrackerStatusInterface.VendorTrackerStatus();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}