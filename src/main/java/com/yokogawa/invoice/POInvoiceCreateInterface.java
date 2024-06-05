package com.yokogawa.invoice;

public interface POInvoiceCreateInterface {

    void VendorCreatePOInvoice();
    double VendorGST();
    double VendorTotalGST();
    void SGDEquivalentEnable(double finalTotalGSTPercentage, double finalGSTPercentage);
}