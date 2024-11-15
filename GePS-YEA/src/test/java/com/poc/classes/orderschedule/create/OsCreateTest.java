package com.poc.classes.orderschedule.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class OsCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iOsCreate.create();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}