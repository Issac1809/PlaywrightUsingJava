package com.procurement.dispatchnotes.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DnEditTest extends BaseTest {

    @Test
    public void DnEditTestmethod(){
        try {
        dnEdit.PocDnEditMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}