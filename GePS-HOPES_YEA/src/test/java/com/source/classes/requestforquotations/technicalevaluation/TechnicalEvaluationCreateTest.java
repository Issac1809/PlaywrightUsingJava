package com.source.classes.requestforquotations.technicalevaluation;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TechnicalEvaluationCreateTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void TechnicalEvaluationCreate(String type){
        try {
            iTeCreate.technicalEvaluationCreate(type);
        } catch (Exception exception) {
            logger.error("Exception in Technical Evaluation Create Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Technical Evaluation Create Test Function: " + exception.getMessage());
        }
    }
}