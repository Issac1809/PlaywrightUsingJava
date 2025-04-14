package com.source.classes.dispatchnotes.dnreturn;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class DnReturnTest extends BaseTest {

    @Test
    public void dnReturn(){
        try {
            iDnReturn.dnReturn();
        }  catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Return Test function: {}", exception.getMessage());
        }
    }
}