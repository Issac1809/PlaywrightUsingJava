package com.poc.classes.requestforquotation.requote;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RequoteTest extends BaseTest {

    @Test
    public void QuotationRequoteTestMethod() {
        try {
            iQuoRequote.requote();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}