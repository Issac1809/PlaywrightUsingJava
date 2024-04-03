package com.yokogawa.requisition.assign;
import com.microsoft.playwright.Page;
public interface PrAssign {
    void BuyerManagerLogin(String emailId, Page page);
    void BuyerManagerAssign(String title, String buyer, Page page);
}
