package com.procurement.poc.purchaseorderrequest.edit;
import com.procurement.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PorEditTest extends BaseTest {

    @Test
    public void edit() {
        try {
            iPorEdit.porEdit();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}