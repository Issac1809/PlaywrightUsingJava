package com.procurement.purchaseorderrequest.create;
import com.base.BaseTest;
import org.testng.annotations.Test;

public class PorCreateTest extends BaseTest {

    @Test
    public void PorCreateTestMethod(){
        try {
        porCreateNonCatalog.BuyerPORCreate();
        porCreateNonCatalog.Justification();
        porCreateNonCatalog.TaxCode();
        porCreateNonCatalog.PORNotes();
        porCreateNonCatalog.PORCreate();
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}