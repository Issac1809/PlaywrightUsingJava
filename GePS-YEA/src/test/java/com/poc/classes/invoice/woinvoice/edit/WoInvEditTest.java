package com.poc.classes.invoice.woinvoice.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvEditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iWoInvEdit.edit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}