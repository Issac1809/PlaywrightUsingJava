package com.source.classes.requestforquotations.technicalevaluation;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TechnicalEvaluationRejectTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void reject(String type){
        try {
            iTeReject.technicalEvaluationReject(type);
            iTeCreate.technicalEvaluationCreate(type);
        } catch (Exception exception) {
            logger.error("Exception in Technical Evaluation Reject Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Technical Evaluation Reject Test Function: " + exception.getMessage());
        }
    }
}