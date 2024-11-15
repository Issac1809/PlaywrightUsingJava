package com.poc.classes.freightforwarderrequests.requote;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class FfrRequoteTest extends BaseTest {

    @Test
    public void requote(){
        try {
            iFfrRequote.requote();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}