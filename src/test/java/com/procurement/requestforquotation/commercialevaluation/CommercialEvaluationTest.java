package com.procurement.requestforquotation.commercialevaluation;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class CommercialEvaluationTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void CommercialEvaluationTestMethod(){
        try {
        commercialEvaluationInterface.CommercialEvaluationButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
