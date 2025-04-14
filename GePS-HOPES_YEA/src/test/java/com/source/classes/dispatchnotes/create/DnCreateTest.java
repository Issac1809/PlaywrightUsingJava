package com.source.classes.dispatchnotes.create;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DnCreateTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void create(){
        try {
            String type = "";
            String purchaseType = "";

            iDnCreate.create(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Create Test function: {}", exception.getMessage());
        }
    }
}