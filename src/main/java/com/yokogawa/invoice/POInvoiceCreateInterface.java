package com.yokogawa.invoice;
import com.microsoft.playwright.Page;
public interface POInvoiceCreateInterface {
    void VendorCreatePOInvoice(String mailId, String poReferenceId, String InvoiceNumber, Page page);
}
