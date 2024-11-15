package com.poc.classes.dispatchnotes.dnreturn;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DnReturnTest extends BaseTest {

    @Test
    public void dnReturn(){
        try {
            iDnReturn.dnReturn();
        }  catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}