package com.procurement.orderschedule.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class OSCreateTest extends BaseTest {

    @Test
    public void OSCreateTestMethod(){
        try {
        orderScheduleInterface.OSCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}