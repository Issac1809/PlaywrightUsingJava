package org.requisition.assign;
import com.microsoft.playwright.Page;
public interface PrAssign {
    void BuyerManagerLogin(String mailId, String password, Page page);
    void BuyerManagerAssign(String title, String buyerManager, String buyer, Page page);
}
