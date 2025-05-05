package com.source.classes.inspections.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsCreateTest extends BaseTest {

    @Test
    public void create(){
        try {
            iInsCreate.create();
        } catch (Exception exception) {
            logger.error("Exception in Inspection Create Test function: {}", exception.getMessage());
            Assert.fail("Exception in Inspection Create Test Function: " + exception.getMessage());
        }
    }
}