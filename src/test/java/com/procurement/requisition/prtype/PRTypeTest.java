package com.procurement.requisition.prtype;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PRTypeTest extends BaseTest {

    @Test
    public void PRTypeTestMethod() throws InterruptedException {
        try {
            page.pause();
            iPrType.RequesterLoginPRCreate();
            iPrType.CreateButton();
            iPrType.PurchaseType();
            iPrType.Title();
            iPrType.TransactionType();
        } catch (Exception error) {
            System.out.println("What is the Error: " + error);
        }
    }
}