package com.source.classes.requestforquotation.commercialevaluation;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CommercialEvaluationTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void ceCreate(){
        try {
            String type = "";
            int status = iCeCreate.commercialEvaluationButton(type);
            Assert.assertEquals(200, status, "Commercial Evaluation Create was not successful");
        } catch (Exception exception) {
            logger.error("Exception in Commercial Evaluation Test Function: {}", exception.getMessage());
            Assert.fail("Exception in Commercial Evaluation Test Function: " + exception.getMessage());
        }
    }
}