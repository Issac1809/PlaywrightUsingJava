package com.procurement.dispatchnotes.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DispatchNotesCreateTest extends BaseTest {

    @Test
    public void DispatchNotesCreateTestMethod(){
        try {
        dispatchNoteCreateInterface.DNCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}