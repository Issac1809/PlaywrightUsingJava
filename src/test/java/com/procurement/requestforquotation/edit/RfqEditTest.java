package com.procurement.requestforquotation.edit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class RfqEditTest extends BaseTest {

    @Test
    public void RfqEditTestMethod() throws InterruptedException {
        try {
        rfqEdit.RfqEditMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}