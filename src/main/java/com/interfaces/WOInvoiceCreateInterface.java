package com.interfaces;

public interface WOInvoiceCreateInterface {

    void VendorCreateWOInvoice();
    double VendorGST();
    void SGDEquivalentEnable(double finalGSTPercentage);
}