package com.poc.classes.invoice.poinvoice.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvEditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iInvEdit.edit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}