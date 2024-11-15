package com.poc.classes.workorders.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iWoCreate.create();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}