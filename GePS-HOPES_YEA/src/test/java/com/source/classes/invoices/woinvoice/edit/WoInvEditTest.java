package com.source.classes.invoices.woinvoice.edit;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class WoInvEditTest extends BaseTest {

    @Test
    public void edit(){
        try {
            iWoInvEdit.edit();
        }  catch (Exception exception) {
            logger.error("Exception in WO Invoice Edit Test function: {}", exception.getMessage());
        }
    }
}