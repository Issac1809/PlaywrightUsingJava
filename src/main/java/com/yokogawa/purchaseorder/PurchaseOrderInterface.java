package com.yokogawa.purchaseorder;
import com.microsoft.playwright.Page;
public interface PurchaseOrderInterface {
    void SendForVendor(String mailId, Page page);
}
