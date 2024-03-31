package org.purchaseorderrequest.create;

import com.microsoft.playwright.Page;

public interface PorCreateCatalog {
    void BuyerLogin(String mailId, String password, Page page);
    void BuyerPORCreate(String title, Page page);
    void TaxCode(Page page);
    void PORNotes(String notes, Page page);
    void PORCreate(Page page);
}
