package com.procurement.requestforquotation.readyforevaluation;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class ReadyForEvaluationTest extends BaseTest {

    @Test
    public void ReadyForEvaluationTestMethod(){
        try {
        readyForEvalutationInterface.ReadyForEvaluationButton();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}