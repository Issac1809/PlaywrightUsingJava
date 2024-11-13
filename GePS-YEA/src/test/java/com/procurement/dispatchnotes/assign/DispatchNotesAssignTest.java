package com.procurement.dispatchnotes.assign;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class DispatchNotesAssignTest extends BaseTest {

    @Test
    public void DispatchNotesAssignTestMethod(){
        try {
        dispatchNotesAssignInterface.DNAssign();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}