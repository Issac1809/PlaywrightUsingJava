package com.source.classes.orderschedules.create;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OsCreateTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void create(){
        try {
            String type = "";
            String purchaseType = "";

            iOsCreate.create(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in OS Create Test Function: {}", exception.getMessage());
        }
    }
}