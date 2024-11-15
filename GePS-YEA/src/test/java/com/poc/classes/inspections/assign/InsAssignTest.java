package com.poc.classes.inspections.assign;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InsAssignTest extends BaseTest {

    @Test
    public void assign(){
        try {
            iInsAssign.assign();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}