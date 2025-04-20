package com.source.classes.purchaseorderrequests.create;
import com.base.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PorCreateTest extends BaseTest {

    @Test
    @Parameters({"type", "purchaseType"})
    public void create(String type, String purchaseType){
        try {
            if (type.equalsIgnoreCase("Catalog")) {
                iPorCreate.porCreateButtonForCatalog(type, purchaseType);
            } else if (type.equalsIgnoreCase("NonCatalog")) {
                iPorCreate.porCreateButtonForNonCatalog(type);
            }
            iPorCreate.taxCode();
            iPorCreate.porNotes();
            iPorCreate.createButton(type);
        } catch (Exception exception) {
            logger.error("Exception in POR Create Test Function: {}", exception.getMessage());
        }
    }
}