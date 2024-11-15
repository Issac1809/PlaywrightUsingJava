package com.poc.classes.dispatchnotes.assign;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DnAssignTest extends BaseTest {

    @Test
    public void assign(){
        try {
            iDnAssign.assign();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}