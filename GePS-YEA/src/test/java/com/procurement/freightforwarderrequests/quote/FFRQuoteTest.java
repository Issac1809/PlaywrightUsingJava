package com.procurement.freightforwarderrequests.quote;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class FFRQuoteTest extends BaseTest {

    @Test
    public void FFRQuoteTestMethod(){
        try {
            ffrQuotation.QuoteMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}