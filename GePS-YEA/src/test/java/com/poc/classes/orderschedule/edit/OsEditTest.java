package com.poc.classes.orderschedule.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class OsEditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iOsEdit.edit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}