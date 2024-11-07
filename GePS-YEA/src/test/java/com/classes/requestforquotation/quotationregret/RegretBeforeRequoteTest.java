package com.classes.requestforquotation.quotationregret;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RegretBeforeRequoteTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void RegretBeforeRequoteTestMethod(){
        try {
        quotationRegret.Regret();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}