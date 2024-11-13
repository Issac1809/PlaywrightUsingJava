package com.procurement.inspections.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InspectionCreateTest extends BaseTest {

    @Test
    public void InspectionCreateTestMethod(){
        try {
        inspectionCreateInterface.VendorInspectionCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}