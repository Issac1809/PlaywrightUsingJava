package com.poc.classes.freightforwarderrequests.quote;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class FfrQuoteTest extends BaseTest {

    @Test
    public void quote(){
        try {
            iFfrQuote.quote();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}