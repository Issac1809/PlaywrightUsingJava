package com.poc.classes.invoice.woinvoice.revert;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvRevertTest extends BaseTest {

    @Test
    public void revert() {
        try {
            iWoInvRevert.revert();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}