package com.source.classes.requestforquotation.readyforevaluation;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReadyForEvaluationTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void readyForEvaluation(){
        try {
            String type = "";
            iReadyForEvalutation.readyForEvaluationButton(type);
        } catch (Exception exception) {
            logger.error("Exception in Ready For Evaluation Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Ready For Evaluation Test Function: " + exception.getMessage());
        }
    }
}