package com.poc.classes.inspections.fail;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InsFailTest extends BaseTest {

    @Test
    public void fail(){
        try {
            iInsFail.fail();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}