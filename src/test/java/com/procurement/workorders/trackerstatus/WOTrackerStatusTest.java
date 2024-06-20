package com.procurement.workorders.trackerstatus;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WOTrackerStatusTest extends BaseTest {

    @Test
    public void WOTrackerStatusTestMethod(){
        try {
        workOrderCreateInterface.WOCreate();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}