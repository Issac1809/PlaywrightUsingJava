package com.poc.interfaces.inv.woinv;

public interface WOInvoiceCreateInterface {

    void VendorCreateWOInvoice();
    double VendorGST();
    void SGDEquivalentEnable(double finalGSTPercentage);
}