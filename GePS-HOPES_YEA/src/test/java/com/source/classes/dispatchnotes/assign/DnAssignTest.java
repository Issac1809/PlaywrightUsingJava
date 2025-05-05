package com.source.classes.dispatchnotes.assign;
import com.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DnAssignTest extends BaseTest {

    @Test
    public void assign(){
        try {
            iDnAssign.assign();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Assign Test function: {}", exception.getMessage());
            Assert.fail("Exception in DN Assign Test Function: " + exception.getMessage());

        }
    }
}