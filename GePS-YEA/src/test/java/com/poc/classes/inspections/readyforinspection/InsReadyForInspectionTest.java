package com.poc.classes.inspections.readyforinspection;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InsReadyForInspectionTest extends BaseTest {

    @Test
    public void readyForInspection(){
        try {
            iInsReadyForInspection.readyForInspection();
        } catch (Exception exception) {
            System.out.println("What is the error: " + exception.getMessage());
        }
    }
}