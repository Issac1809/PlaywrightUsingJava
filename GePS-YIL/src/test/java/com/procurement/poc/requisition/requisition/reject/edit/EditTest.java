package com.procurement.poc.requisition.requisition.reject.edit;
import com.base.BaseTest;
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