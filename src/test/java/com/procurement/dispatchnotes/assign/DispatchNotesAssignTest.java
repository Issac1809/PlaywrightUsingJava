package com.procurement.dispatchnotes.assign;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class DispatchNotesAssignTest extends BaseTest {

    @Test
    public void DispatchNotesAssignTestMethod(){
        try {
        dispatchNotesAssignInterface.DNAssign();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}