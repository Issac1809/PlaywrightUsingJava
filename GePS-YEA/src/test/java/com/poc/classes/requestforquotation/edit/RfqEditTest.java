package com.poc.classes.requestforquotation.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RfqEditTest extends BaseTest {

    @Test
    public void edit() {
        try {
            iRfqEdit.rfqEditMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}