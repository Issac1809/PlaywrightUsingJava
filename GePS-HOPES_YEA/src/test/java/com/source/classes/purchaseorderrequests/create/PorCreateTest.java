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

            if (type.equalsIgnoreCase("Catalog")) {
                type = "Catalog";
                iPorCreate.porCreateButtonForCatalog(type, purchaseType);
            } else if (type.equalsIgnoreCase("NonCatalog")) {
                type = "Non-Catalog";
                iPorCreate.porCreateButtonForNonCatalog(type);
            } else {
                throw new IllegalArgumentException("Invalid type: " + type);
            }
            iPorCreate.taxCode();
            iPorCreate.porNotes();
            iPorCreate.porCreate();
        } catch (Exception exception) {
            logger.error("Exception in POR Create Test Function: {}", exception.getMessage());
        }
    }
}