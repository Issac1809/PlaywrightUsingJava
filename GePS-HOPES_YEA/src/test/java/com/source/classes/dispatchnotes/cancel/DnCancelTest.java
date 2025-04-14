package com.source.classes.dispatchnotes.cancel;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DnCancelTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void cancel(){
        try {
            String type = "";
            String purchaseType = "";

            iDnCancel.cancel(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in Dispatch Notes Cancel Test function: {}", exception.getMessage());
        }
    }
}