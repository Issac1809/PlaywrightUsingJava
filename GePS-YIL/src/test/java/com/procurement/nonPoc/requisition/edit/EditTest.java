package com.procurement.nonPoc.requisition.edit;
import com.procurement.nonPoc.base.BaseTest;
import org.testng.annotations.Test;

public class EditTest extends BaseTest {

    @Test
    public void NonCatalogPrEditTestMethod(){
        try {
            iPrEdit.edit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}