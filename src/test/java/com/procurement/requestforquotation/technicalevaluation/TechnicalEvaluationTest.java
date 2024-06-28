package com.procurement.requestforquotation.technicalevaluation;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class TechnicalEvaluationTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void TechnicalEvaluationTestMethod(){
        try {
        technicalEvaluationInterface.TechnicalEvaluationButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}