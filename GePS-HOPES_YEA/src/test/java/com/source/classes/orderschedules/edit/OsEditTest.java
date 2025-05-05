package com.source.classes.orderschedules.edit;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OsEditTest extends BaseTest {

    @Test
    public void edit() {
        try {
            iOsEdit.edit();
        } catch (Exception exception) {
            logger.error("Exception in OS Edit Test Function: {}", exception.getMessage());
            Assert.fail("Exception in OS Edit Test Function: " + exception.getMessage());
        }
    }
}