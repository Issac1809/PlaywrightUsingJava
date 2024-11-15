package com.poc.classes.orderschedule.reject;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class OsRejectTest extends BaseTest {

    @Test
    public void reject(){
        try {
            iOsReject.reject();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}