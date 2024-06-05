package com.requestforquotation.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RFQCreateTest extends BaseTest {

    @Test
    public void RFQCreateTestMethod() throws InterruptedException {
        rfqCreate.BuyerLogin();
        rfqCreate.BuyerRfqCreate();
        rfqCreate.RfQNotes();
        rfqCreate.RFQCreate();
    }
}
