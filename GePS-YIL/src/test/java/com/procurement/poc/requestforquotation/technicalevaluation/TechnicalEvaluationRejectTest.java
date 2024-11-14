package com.procurement.poc.requestforquotation.technicalevaluation;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class TechnicalEvaluationRejectTest extends BaseTest {

    @Test
    public void reject(){
        try {
            iTeReject.technicalEvaluationRejectMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}