package com.poc.classes.requisition.edit;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class EditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iPrEdit.edit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}