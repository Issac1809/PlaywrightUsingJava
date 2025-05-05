package com.source.classes.inspections.assign;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsAssignTest extends BaseTest {

    @Test
    public void assign(){
        try {
            iInsAssign.assign();
        } catch (Exception exception) {
            logger.error("Exception in Inspection Assign Test function: {}", exception.getMessage());
            Assert.fail("Exception in Inspection Assign Test Function: " + exception.getMessage());
        }
    }
}