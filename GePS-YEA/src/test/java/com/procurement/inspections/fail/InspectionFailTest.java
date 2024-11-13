package com.procurement.inspections.fail;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InspectionFailTest extends BaseTest {

    @Test
    public void InspectionFailTestMethod(){
        try {
            insFail.RequesterInspectionFail();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}