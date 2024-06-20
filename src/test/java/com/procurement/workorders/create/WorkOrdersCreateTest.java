package com.procurement.workorders.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WorkOrdersCreateTest extends BaseTest {

    @Test
    public void WorkOrdersCreateTestMethod(){
        try {
        workOrderCreateInterface.WOCreate();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}