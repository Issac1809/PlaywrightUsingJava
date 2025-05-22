package com.source.interfaces.invoices.poinvoices;

public interface IInvChecklistReject {

    void reject(String referenceId, String transactionId, String uid);
}