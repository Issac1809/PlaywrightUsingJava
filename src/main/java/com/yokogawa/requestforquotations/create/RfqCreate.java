package com.yokogawa.requestforquotations.create;

public interface RfqCreate {
    void BuyerLogin();
    void BuyerRfqCreate();
    void RfQNotes();
    void RFQCreate() throws InterruptedException;
}