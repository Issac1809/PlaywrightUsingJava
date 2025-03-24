package com.source.classes.requestforquotation.create;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RfqCreateTest extends BaseTest {

    @Test
    @Parameters({"type"})
    public void create() {
        try {
            iRfqCreate.buyerRfqCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}