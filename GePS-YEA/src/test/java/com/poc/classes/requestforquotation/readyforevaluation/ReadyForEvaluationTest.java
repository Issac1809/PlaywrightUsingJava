package com.poc.classes.requestforquotation.readyforevaluation;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class ReadyForEvaluationTest extends BaseTest {

    @Test
    public void readyForEvaluation(){
        try {
            iReadyForEvalutation.readyForEvaluationButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}