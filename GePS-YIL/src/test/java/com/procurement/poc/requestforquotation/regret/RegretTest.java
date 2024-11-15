package com.procurement.poc.requestforquotation.regret;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class RegretTest extends BaseTest {

    @Test
    public void regret(){
        try {
            iQuoRegret.regret();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}