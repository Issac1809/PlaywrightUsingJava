package com.procurement.dispatchnotes.cancel;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DnCancelTest extends BaseTest{

    @Test
    public void DnCancelTestMethod(){
        try {
            dnCancel.PocDnCancelMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}