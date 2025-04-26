package com.source.interfaces.purchaseorderrequests;

public interface IPorApprove {

    void savePorAprovers(String type, String purchaseType);
    void approve(String type, String purchaseType);
}