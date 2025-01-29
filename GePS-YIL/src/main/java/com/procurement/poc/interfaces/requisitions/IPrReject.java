package com.procurement.poc.interfaces.requisitions;

public interface IPrReject {
    void reject(String approver) throws InterruptedException;
}