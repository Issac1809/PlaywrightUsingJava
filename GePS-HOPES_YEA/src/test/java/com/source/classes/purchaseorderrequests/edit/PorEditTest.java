package com.source.classes.purchaseorderrequests.edit;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorEditTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void edit(String type, String purchaseType) {
        try {
            iPorEdit.porEdit(type, purchaseType);
        } catch (Exception exception) {
            logger.error("Exception in POR Edit Test Function: {}", exception.getMessage());
        }
    }
}