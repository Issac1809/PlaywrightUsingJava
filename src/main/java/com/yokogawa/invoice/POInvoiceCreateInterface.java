package com.yokogawa.invoice;
import com.microsoft.playwright.Page;
public interface POInvoiceCreateInterface {
    String VendorCreatePOInvoice(String mailId, String poReferenceId, String InvoiceNumber, Page page) throws InterruptedException;
    double VendorGST(Page page);
    double VendorTotalGST(Page page);
    void SGDEquivalentEnable(String currencyCode, double finalTotalGSTPercentage, double finalGSTPercentage, String poReferenceId, Page page);
//    void GSTEquivalentInputField(double finalCurrencyExchangeRate, double finalTotalGSTPercentage, Page page);
}