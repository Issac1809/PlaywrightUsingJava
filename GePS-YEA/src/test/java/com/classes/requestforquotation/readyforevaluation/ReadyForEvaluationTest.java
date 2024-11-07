package com.classes.requestforquotation.readyforevaluation;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class ReadyForEvaluationTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void ReadyForEvaluationTestMethod(){
        try {
        readyForEvalutationInterface.ReadyForEvaluationButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}