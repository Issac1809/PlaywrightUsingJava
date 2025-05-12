package com.source.classes.dispatchnotes.edit;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DnEditTest extends BaseTest {

    @Epic("Dispatch Notes")
    @Feature("Dispatch Notes Edit")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Vendor Can Edit The Dispatch Notes")
    @Test(description = "Dispatch Notes Edit Test")
    public void edit(){
        try {
            iDnEdit.edit();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Edit Test function: {}", exception.getMessage());
            Assert.fail("Exception in DN Edit Test Function: " + exception.getMessage());
        }
    }
}