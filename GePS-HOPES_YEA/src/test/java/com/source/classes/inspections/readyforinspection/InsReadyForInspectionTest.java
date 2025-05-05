package com.source.classes.inspections.readyforinspection;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InsReadyForInspectionTest extends BaseTest {

    @Test
    public void readyForInspection(){
        try {
            iInsReadyForInspection.readyForInspection();
        } catch (Exception exception) {
            logger.error("Exception in Ready for Inspection Test function: {}", exception.getMessage());
            Assert.fail("Exception in Ready For Inspection Test Function: " + exception.getMessage());
        }
    }
}