package com.procurement.poc.requestforquotation.quote;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class QuoteTest extends BaseTest {

    @Test
    public void quote() {
        try {
            iQuoSubmit.inviteRegisteredVendor();
            iQuoSubmit.quotationCreateAndSubmit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}