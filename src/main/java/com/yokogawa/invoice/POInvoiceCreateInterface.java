package com.yokogawa.invoice;

public interface POInvoiceCreateInterface {

    void VendorCreatePOInvoice();
    double VendorGST();
    void SGDEquivalentEnable(double finalGSTPercentage);
}