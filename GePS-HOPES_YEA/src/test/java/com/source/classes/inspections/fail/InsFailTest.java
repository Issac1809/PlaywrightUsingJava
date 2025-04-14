package com.source.classes.inspections.fail;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class InsFailTest extends BaseTest {

    @Test
    public void fail(){
        try {
            iInsFail.fail();
        } catch (Exception exception) {
            logger.error("Exception in Inspection Fail Test function: {}", exception.getMessage());
        }
    }
}