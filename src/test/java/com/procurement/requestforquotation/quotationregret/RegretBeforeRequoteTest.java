package com.procurement.requestforquotation.quotationregret;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RegretBeforeRequoteTest extends BaseTest {

    @Test
    public void RegretBeforeRequoteTestMethod(){
        try {
        quotationRegret.Regret();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}