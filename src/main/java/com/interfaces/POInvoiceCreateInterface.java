package com.interfaces;

public interface POInvoiceCreateInterface {

    void VendorCreatePOInvoice();
    double VendorGST();
    void SGDEquivalentEnable(double finalGSTPercentage);
}