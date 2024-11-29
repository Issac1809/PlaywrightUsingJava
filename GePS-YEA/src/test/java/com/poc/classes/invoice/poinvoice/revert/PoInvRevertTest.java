package com.poc.classes.invoice.poinvoice.revert;
import com.poc.base.BaseTest;
import org.testng.annotations.Test;

public class PoInvRevertTest extends BaseTest {

    @Test
    public void revert() {
        try {
            iInvRevert.revert();
        } catch (Exception error) {
            System.out.println("What is the error: " + error.getMessage());
        }
    }
}