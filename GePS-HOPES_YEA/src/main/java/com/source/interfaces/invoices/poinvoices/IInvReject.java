package com.source.interfaces.invoices.poinvoices;

public interface IInvReject {

    void reject(String referenceId, String transactionId, String uid);
}