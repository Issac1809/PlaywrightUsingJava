package com.poc.classes.invoice.poinvoice.invreturn;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvReturnTest extends BaseTest {

    @Test
    public void returnMethod(){
        try {
            iInvReturn.invReturn();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}