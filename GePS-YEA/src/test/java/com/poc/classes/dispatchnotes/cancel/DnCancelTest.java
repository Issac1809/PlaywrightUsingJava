package com.poc.classes.dispatchnotes.cancel;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DnCancelTest extends BaseTest {

    @Test
    public void cancel(){
        try {
            iDnCancel.cancel();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}