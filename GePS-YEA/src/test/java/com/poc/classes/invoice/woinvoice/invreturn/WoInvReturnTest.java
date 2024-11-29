package com.poc.classes.invoice.woinvoice.invreturn;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvReturnTest extends BaseTest {

    @Test
    public void returnMethod(){
        try {
            iWoInvReturn.returnMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}