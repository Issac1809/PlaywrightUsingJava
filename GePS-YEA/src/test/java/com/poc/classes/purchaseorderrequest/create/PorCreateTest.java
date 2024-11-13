package com.poc.classes.purchaseorderrequest.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iPorCreate.buyerPorCreate();
            iPorCreate.justification();
            iPorCreate.taxCode();
            iPorCreate.porNotes();
            iPorCreate.porCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}