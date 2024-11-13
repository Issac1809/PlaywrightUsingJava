package com.procurement.msa;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class InspectPOTest extends BaseTest {

    @Test
    public void InspectPOTestMethod(){
        try {
        porInspectPoInterface.InspectCreatePO();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}