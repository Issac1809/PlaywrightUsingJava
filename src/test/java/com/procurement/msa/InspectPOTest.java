package com.procurement.msa;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InspectPOTest extends BaseTest {

    @Test
    public void InspectPOTestMethod(){
        try {
        porInspectPoInterface.InspectCreatePO();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}