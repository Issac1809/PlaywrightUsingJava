package com.procurement.requestforquotation.technicalevaluation;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class TechnicalEvaluationRejectTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void TechnicalEvaluationRejectTestMethod(){
        try {
        teReject.TechnicalEvaluationRejectMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}