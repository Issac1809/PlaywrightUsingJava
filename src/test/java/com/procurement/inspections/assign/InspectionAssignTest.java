package com.procurement.inspections.assign;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InspectionAssignTest extends BaseTest {

    @Test
    public void InspectionAssignTestMethod(){
        try {
        inspectionAssignInterface.RequesterInspectionAssign();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}