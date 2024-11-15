package com.procurement.poc.purchaseorderrequest.create;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iPorCreate.catalogPORCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}