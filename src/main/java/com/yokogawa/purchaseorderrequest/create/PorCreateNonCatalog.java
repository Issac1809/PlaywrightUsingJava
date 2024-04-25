package com.yokogawa.purchaseorderrequest.create;
import com.microsoft.playwright.Page;
public interface PorCreateNonCatalog {
    void BuyerPORCreate(Page page);
    void Justification(Page page);
    void TaxCode(String taxCode, Page page);
    void PORNotes(String notes, Page page);
    void PORCreate(Page page) throws InterruptedException;
}
