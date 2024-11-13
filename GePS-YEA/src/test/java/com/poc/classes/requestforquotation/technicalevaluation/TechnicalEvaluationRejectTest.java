package com.poc.classes.requestforquotation.technicalevaluation;
import com.poc.base.BaseTest;
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