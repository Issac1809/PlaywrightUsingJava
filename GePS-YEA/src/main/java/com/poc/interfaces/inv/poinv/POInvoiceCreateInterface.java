package com.poc.interfaces.inv.poinv;

public interface POInvoiceCreateInterface {

    void VendorCreatePOInvoice();
    double VendorGST();
    void SGDEquivalentEnable(double finalGSTPercentage);
}