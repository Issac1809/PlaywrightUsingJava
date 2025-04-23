package com.source.classes.requestforquotations.technicalevaluation;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TechnicalEvaluationApproveTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void TechnicalEvaluationApprove(String type){
        try {
            int status = iTeApprove.technicalEvaluationApprove(type);
            Assert.assertEquals(200, status, "Requisition Approve was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Technical Evaluation Approve Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Technical Evaluation Approve Test Function: " + exception.getMessage());
        }
    }
}