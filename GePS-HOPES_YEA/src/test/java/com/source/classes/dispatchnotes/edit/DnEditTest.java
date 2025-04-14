package com.source.classes.dispatchnotes.edit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class DnEditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iDnEdit.edit();
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Edit Test function: {}", exception.getMessage());
        }
    }
}