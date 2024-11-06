package com.procurement.requestforquotation.quotationrequote;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class QuotationRequoteTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void QuotationRequoteTestMethod() throws InterruptedException{
        try {
        quotationRequote.Requote();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}