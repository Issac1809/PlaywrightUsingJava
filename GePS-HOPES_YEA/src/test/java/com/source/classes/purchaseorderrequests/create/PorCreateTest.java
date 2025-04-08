package com.source.classes.purchaseorderrequests.create;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorCreateTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void create(){
        try {
            String type = "";
            String purchaseType = "";

            iPorCreate.porCreateButtonForCatalog(type, purchaseType);
            iPorCreate.porCreateButtonForNonCatalog(type);
            iPorCreate.justification();
            iPorCreate.taxCode();
            iPorCreate.porNotes();
            iPorCreate.porCreate();
        } catch (Exception exception) {
            logger.error("Exception in POR Create Test Function: {}", exception.getMessage());
        }
    }
}