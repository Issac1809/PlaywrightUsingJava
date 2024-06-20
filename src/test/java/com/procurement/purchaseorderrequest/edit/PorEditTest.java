package com.procurement.purchaseorderrequest.edit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PorEditTest extends BaseTest {

    @Test
    public void PorEditTestMethod() throws InterruptedException{
        try {
        porEdit.PorEditMethod();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}