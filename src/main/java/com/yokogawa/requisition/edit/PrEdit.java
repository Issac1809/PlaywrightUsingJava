package com.yokogawa.requisition.edit;

public interface PrEdit {

    void PrEditMethod() throws InterruptedException;
    void PrRejectEdit() throws InterruptedException;
    void PrBuyerManagerSuspendEdit() throws InterruptedException;
    void PrBuyerSuspendEdit() throws InterruptedException;
}