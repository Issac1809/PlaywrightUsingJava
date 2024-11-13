package com.procurement.freightforwarderrequests.requote;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class FFRRequoteTest extends BaseTest {

    @Test
    public void FFRRequoteTestMethod(){
        try {
            ffrRequote.RequoteMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}