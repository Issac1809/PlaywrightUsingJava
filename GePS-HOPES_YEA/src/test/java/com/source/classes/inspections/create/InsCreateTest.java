package com.source.classes.inspections.create;
import com.base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsCreateTest extends BaseTest {

    @Epic("Inspections")
    @Feature("Inspection Create")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description: Verify Requester Can Create The Inspection")
    @Test(description = "Inspections Create Test")
    public void create(){
        try {
            iInsCreate.create();
        } catch (Exception exception) {
            logger.error("Exception in Inspection Create Test function: {}", exception.getMessage());
            Assert.fail("Exception in Inspection Create Test Function: " + exception.getMessage());
        }
    }
}