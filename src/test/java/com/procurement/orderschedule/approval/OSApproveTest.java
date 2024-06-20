package com.procurement.orderschedule.approval;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class OSApproveTest extends BaseTest {

    @Test
    public void OSApproveTestMethod(){
        try {
        orderScheduleApproveInterface.OSApprove();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
