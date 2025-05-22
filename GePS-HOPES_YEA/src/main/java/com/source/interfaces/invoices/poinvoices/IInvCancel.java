package com.source.interfaces.invoices.poinvoices;

public interface IInvCancel {

    void cancel(String referenceId, String transactionId, String uid);
}