package com.poc.classes.orderschedule.approval;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class OsApproveTest extends BaseTest {

    @Test
    public void approve(){
        try {
            iOsApprove.approve();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}