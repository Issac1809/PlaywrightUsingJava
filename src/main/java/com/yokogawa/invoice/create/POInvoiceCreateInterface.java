package com.yokogawa.invoice.create;

public interface POInvoiceCreateInterface {

    void VendorCreatePOInvoice();
    double VendorGST();
    void SGDEquivalentEnable(double finalGSTPercentage);
}