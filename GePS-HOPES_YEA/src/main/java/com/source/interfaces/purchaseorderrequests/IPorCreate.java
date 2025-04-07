package com.source.interfaces.purchaseorderrequests;

public interface IPorCreate {
    void porCreateButtonForCatalog(String type, String purchaseType);
    void porCreateButtonForNonCatalog(String type);
    void justification();
    void taxCode();
    void porNotes();
    void porCreate();
}