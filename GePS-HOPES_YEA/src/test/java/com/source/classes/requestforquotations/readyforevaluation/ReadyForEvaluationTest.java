package com.source.classes.requestforquotations.readyforevaluation;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ReadyForEvaluationTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void readyForEvaluation(String type){
        try {
            int status = iReadyForEvalutation.readyForEvaluationButton(type);
            Assert.assertEquals(200, status, "Requisition Approve was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Ready For Evaluation Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Ready For Evaluation Test Function: " + exception.getMessage());
        }
    }
}