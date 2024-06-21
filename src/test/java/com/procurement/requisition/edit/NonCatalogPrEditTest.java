package com.procurement.requisition.edit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class NonCatalogPrEditTest extends BaseTest {

    @Test (groups = "requisition")
    public void NonCatalogPrEditTestMethod(){
        try {
            prEdit.PrEditMethod();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}