package com.source.classes.workorders.create;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WoCreateTest extends BaseTest {

    @Test
    public void create() {
        try {
            iWoCreate.create();
        } catch (Exception exception) {
            logger.error("Exception in Work Order Create Test function: {}", exception.getMessage());
            Assert.fail("Exception in WO Create Test Function: " + exception.getMessage());
        }
    }
}