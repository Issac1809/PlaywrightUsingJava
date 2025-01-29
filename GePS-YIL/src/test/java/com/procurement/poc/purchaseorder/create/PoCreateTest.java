package com.procurement.poc.purchaseorder.create;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iPoCreate.createPO();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
