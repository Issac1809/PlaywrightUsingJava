package com.procurement.poc.purchaseorder.create;
import com.procurement.poc.base.BaseTest;

public class PoCreateTest extends BaseTest {

    public void create(){
        try {
            iPoCreate.createPO();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}
