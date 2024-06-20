package com.procurement.requestforquotation.technicalevaluation;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class TechnicalEvaluationRejectTest extends BaseTest {

    @Test
    public void TechnicalEvaluationRejectTestMethod(){
        try {
        teReject.TechnicalEvaluationRejectMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}