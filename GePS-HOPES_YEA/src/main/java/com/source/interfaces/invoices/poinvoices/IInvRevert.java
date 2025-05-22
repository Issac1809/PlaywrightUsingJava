package com.source.interfaces.invoices.poinvoices;

public interface IInvRevert {

    void revert(String referenceId, String transactionId, String uid);
}