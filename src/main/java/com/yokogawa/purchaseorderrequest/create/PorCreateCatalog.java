package com.yokogawa.purchaseorderrequest.create;
import com.microsoft.playwright.Page;
public interface PorCreateCatalog {
    void BuyerLogin(String mailId,Page page);
    void BuyerPORCreate(String title, Page page);
    void TaxCode(String taxCode, Page page);
    void PORNotes(String notes, Page page);
    void PORCreate(Page page);
}
