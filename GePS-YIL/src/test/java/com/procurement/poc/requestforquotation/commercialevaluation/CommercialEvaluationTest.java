package com.procurement.poc.requestforquotation.commercialevaluation;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class CommercialEvaluationTest extends BaseTest {

    @Test
    public void ceCreate(){
        try {
            iCeCreate.commercialEvaluationButton();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}