package com.procurement.sales.requisition.edit;

import com.procurement.sales.base.BaseTest;
import org.testng.annotations.Test;

public class EditTest extends BaseTest {

    @Test
    public void PrEditTestMethod(){
        try {
            iPrEdit.edit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
