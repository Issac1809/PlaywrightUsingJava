package com.source.interfaces.purchaseorderrequests;

public interface IPorSuspend {

    void suspend(String type, String purchaseType);
    void suspendPorEdit(String type, String purchaseType);
    void suspendRfqOrPrEdit(String type, String purchaseType);
}