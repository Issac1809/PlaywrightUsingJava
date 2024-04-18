package com.yokogawa.requestforquotations.create;

import com.microsoft.playwright.Page;

public interface RfqCreate {
    void BuyerLogin(String mailId, Page page);
    void BuyerRfqCreate(String title, Page page);
    void RfQNotes(String notes, Page page);
    void RFQCreate(Page page) throws InterruptedException;
}