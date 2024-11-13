package com.poc.classes.requestforquotation.create;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RfqCreateTest extends BaseTest {

    @Test
    public void create() {
        try {
            iRfqCreate.buyerLogin();
            iRfqCreate.buyerRfqCreate();
            iRfqCreate.rfqNotes();
            iRfqCreate.rfqCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}