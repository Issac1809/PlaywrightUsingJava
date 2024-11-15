package com.poc.classes.inspections.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InsCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iInsCreate.create();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}