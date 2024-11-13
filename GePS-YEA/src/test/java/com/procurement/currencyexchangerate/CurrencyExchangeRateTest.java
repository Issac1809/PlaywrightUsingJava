package com.procurement.currencyexchangerate;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class CurrencyExchangeRateTest extends BaseTest{

    @Test
    public void CurrencyExchangeRateTestMethod(){
        try {
            currencyExchangeRate.findCurrency();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}