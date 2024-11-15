package com.poc.classes.dispatchnotes.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DnCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iDnCreate.create();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}