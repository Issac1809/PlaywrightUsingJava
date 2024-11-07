package com.classes.requestforquotation.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RFQCreateTest extends BaseTest {

    @Test (groups = "requestforquotations")
    public void RFQCreateTestMethod() throws InterruptedException {
        try {
        rfqCreate.BuyerLogin();
        rfqCreate.BuyerRfqCreate();
        rfqCreate.RfQNotes();
        rfqCreate.RFQCreate();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}