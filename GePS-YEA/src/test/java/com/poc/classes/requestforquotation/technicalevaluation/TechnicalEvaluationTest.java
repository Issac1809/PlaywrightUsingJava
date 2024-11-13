package com.poc.classes.requestforquotation.technicalevaluation;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class TechnicalEvaluationTest extends BaseTest {

    @Test
    public void TechnicalEvaluationTestMethod(){
        try {
            iTeCreate.technicalEvaluationButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}